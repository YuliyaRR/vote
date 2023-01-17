package groupwork.service;

import groupwork.dao.GenreDao;
import groupwork.dao.SingerDao;
import groupwork.dao.VotingDao;
import groupwork.dto.VoiceDTO;

import groupwork.service.api.IVotesService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoteServiceTest {
    private final IVotesService votesService = new VoteService(new VotingDao(), new SingerService(new SingerDao()), new GenreService(new GenreDao()));

    @Test
    public void testVotingServiceSave() {
        int singer = 1;
        int[] genre = {1, 2, 3};
        String message = "lololo";
        votesService.save(new VoiceDTO(singer, genre, message));
        boolean result = !votesService.get().isEmpty();
        assertTrue(result);
    }

    @Test
    public void testVotingServiceExceptionSingerIsMissing() {
        int singer = 6;
        int[] genre = {1, 2, 3};
        String message = "lololo";
        int exception = 0;
        boolean result = false;
        try {
            votesService.save(new VoiceDTO(singer, genre, message));
        } catch (Exception e) {
            exception = 1;
        }
        if (exception == 1) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void testVotingServiceExceptionNumberOfGenresMore() {
        int singer = 1;
        int[] genre = {1, 2, 3, 6, 8, 9};
        String message = "lololo";
        int exception = 0;
        boolean result = false;
        try {
            votesService.save(new VoiceDTO(singer, genre, message));
        } catch (Exception e) {
            exception = 1;
        }
        if (exception == 1) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void testVotingServiceExceptionNumberOfGenresLess() {
        int singer = 2;
        int[] genre = {1, 2};
        String message = "lololo";
        int exception = 0;
        boolean result = false;
        try {
            votesService.save(new VoiceDTO(singer, genre, message));
        } catch (Exception e) {
            exception = 1;
        }
        if (exception == 1) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void testVotingServiceExceptionNumberOfGenresDouble() {
        int singer = 2;
        int[] genre = {1, 2, 2};
        String message = "lololo";
        int exception = 0;
        boolean result = false;
        try {
            votesService.save(new VoiceDTO(singer, genre, message));
        } catch (Exception e) {
            exception = 1;
        }
        if (exception == 1) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void testVotingServiceExceptionNumberOfGenresDoesNotExist() {
        int singer = 2;
        int[] genre = {1, 2, 15};
        String message = "lololo";
        int exception = 0;
        boolean result = false;
        try {
            votesService.save(new VoiceDTO(singer, genre, message));
        } catch (Exception e) {
            exception = 1;
        }
        if (exception == 1) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void testVotingServiceExceptionInfoAboutUserDoesNotExist() {
        int singer = 2;
        int[] genre = {1, 2, 3};
        String message = "";
        int exception = 0;
        boolean result = false;
        try {
            votesService.save(new VoiceDTO(singer, genre, message));
        } catch (Exception e) {
            exception = 1;
        }
        if (exception == 1) {
            result = true;
        }
        assertTrue(result);
    }
}