package groupwork.dao;

import groupwork.dao.memory.factory.GenreDaoSingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreDaoTest {


    @Test
    public void testGenreDaoTrue() {
        int numberGenreTrue = 1;
        boolean result = GenreDaoSingleton.getInstance().isContain(numberGenreTrue);
        assertTrue(result);
    }

    @Test
    public void testGenreDaoFalse() {
        int numberGenreFalse = 11;
        boolean result = GenreDaoSingleton.getInstance().isContain(numberGenreFalse);
        assertFalse(result);
    }
}