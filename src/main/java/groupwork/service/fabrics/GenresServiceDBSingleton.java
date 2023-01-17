package groupwork.service.fabrics;


import groupwork.dao.fabrics.GenreDaoDBSingleton;
import groupwork.service.GenreService;
import groupwork.service.api.IGenreService;

public class GenresServiceDBSingleton {
    private volatile static GenreService instance;

    private GenresServiceDBSingleton() {
    }

    public static IGenreService getInstance() {
        if (instance == null) {
            synchronized (GenresServiceDBSingleton.class) {
                if (instance == null) {
                    instance = new GenreService(GenreDaoDBSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
