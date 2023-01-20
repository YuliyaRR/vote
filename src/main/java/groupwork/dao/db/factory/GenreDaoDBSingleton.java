package groupwork.dao.fabrics;

import groupwork.dao.db.GenreDAO_DB;
import groupwork.dao.api.IGenreDao;

public class GenreDaoDBSingleton {
    private volatile static GenreDAO_DB instance;

    private GenreDaoDBSingleton() {
    }

    public static IGenreDao getInstance() {
        if (instance == null) {
            synchronized (GenreDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new GenreDAO_DB();
                }
            }
        }
        return instance;
    }
}
