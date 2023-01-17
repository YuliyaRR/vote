package groupwork.service.fabrics;


import groupwork.dao.fabrics.SingerDaoDBSingleton;
import groupwork.service.SingerService;
import groupwork.service.api.ISingerService;

public class SingersServiceDBSingleton {
    private volatile static SingerService instance;

    private SingersServiceDBSingleton() {
    }

    public static ISingerService getInstance() {
        if (instance == null) {
            synchronized (SingersServiceDBSingleton.class) {
                if (instance == null) {
                    instance = new SingerService(SingerDaoDBSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
