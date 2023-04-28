package groupwork.dao.memory.factory;

import groupwork.dao.memory.GenreDaoMemory;
import groupwork.dao.api.IGenreDao;

public class GenreDaoSingleton {
    private volatile static GenreDaoMemory instance;

    private GenreDaoSingleton() {
    }

    public static IGenreDao getInstance() {
        if (instance == null) {
            synchronized (GenreDaoSingleton.class) {
                if (instance == null) {
                    instance = new GenreDaoMemory();
                }
            }
        }
        return instance;
    }
}
