package groupwork.dao;

import groupwork.dao.fabrics.SingerDaoSingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingerDaoTest {
    @Test
    public void testSingerDaoTrue() {
        int numberSingerTrue = 1;
        boolean result = SingerDaoSingleton.getInstance().isContain(numberSingerTrue);
        assertTrue(result);
    }

    @Test
    public void testSingerDaoFalse() {
        int numberSingerFalse = 5;
        boolean result = SingerDaoSingleton.getInstance().isContain(numberSingerFalse);
        assertFalse(result);
    }


}