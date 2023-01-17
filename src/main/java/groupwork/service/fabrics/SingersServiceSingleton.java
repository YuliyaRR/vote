package groupwork.service.fabrics;


import groupwork.dao.fabrics.SingerDaoSingleton;
import groupwork.service.SingerService;
import groupwork.service.api.ISingerService;

public class SingersServiceSingleton {
    private volatile static SingerService instance;

    private SingersServiceSingleton() {
    }

    public static ISingerService getInstance() {
        if (instance == null) {
            synchronized (SingersServiceSingleton.class) {
                if (instance == null) {
                    instance = new SingerService(SingerDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
