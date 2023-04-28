package groupwork.dao.memory.factory;

import groupwork.dao.memory.SingerDaoMemory;
import groupwork.dao.api.ISingerDao;

public class SingerDaoSingleton {
    private volatile static SingerDaoMemory instance;

    private SingerDaoSingleton() {
    }

    public static ISingerDao getInstance() {
        if (instance == null) {
            synchronized (SingerDaoSingleton.class) {
                if (instance == null) {
                    instance = new SingerDaoMemory();
                }
            }
        }
        return instance;
    }


}
