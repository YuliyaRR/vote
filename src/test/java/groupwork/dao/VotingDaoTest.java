package groupwork.dao;

import groupwork.dao.fabrics.VotingDaoSingleton;
import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VotingDaoTest {
    @Test
    public void testVotingDaoTrue() {
        int singer = 1;
        int[] genre = {1, 2, 3};
        String message = "lololo";
        SavedVoiceDTO savedVoiceDTO = new SavedVoiceDTO(new VoiceDTO(singer, genre, message));
        VotingDaoSingleton.getInstance().save(savedVoiceDTO);
        boolean result;
        result = !VotingDaoSingleton.getInstance().getVoiceList().isEmpty();
        assertTrue(result);
    }
}