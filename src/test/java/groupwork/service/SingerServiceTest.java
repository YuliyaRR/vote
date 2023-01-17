package groupwork.service;

import groupwork.service.fabrics.SingersServiceSingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingerServiceTest {


    @Test
    public void testSingerServiceTrue() {
        boolean result = SingersServiceSingleton.getInstance().checkNumber(1);
        assertTrue(result);
    }

    @Test
    public void testSingerServiceFalse() {
        boolean result = SingersServiceSingleton.getInstance().checkNumber(11);
        assertFalse(result);
    }

}