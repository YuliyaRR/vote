package groupwork.service;

import groupwork.service.fabrics.GenresServiceSingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreServiceTest {


    @Test
    public void testGenreServiceTrue() {
        boolean result = GenresServiceSingleton.getInstance().check(1);
        assertTrue(result);
    }

    @Test
    public void testGenreServiceFalse() {
        boolean result = GenresServiceSingleton.getInstance().check(11);
        assertFalse(result);
    }
}