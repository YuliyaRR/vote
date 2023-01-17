package groupwork.dao.fabrics;

import groupwork.dao.SingerDAO_DB;
import groupwork.dao.api.ISingerDao;

public class SingerDaoDBSingleton {
    private volatile static SingerDAO_DB instance;

    private SingerDaoDBSingleton() {
    }

    public static ISingerDao getInstance() {
        if (instance == null) {
            synchronized (SingerDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new SingerDAO_DB();
                }
            }
        }
        return instance;
    }


}
