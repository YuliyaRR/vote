package groupwork.service.fabrics;


import groupwork.dao.fabrics.GenreDaoSingleton;
import groupwork.service.GenreService;
import groupwork.service.api.IGenreService;

public class GenresServiceSingleton {
    private volatile static GenreService instance;

    private GenresServiceSingleton() {
    }

    public static IGenreService getInstance() {
        if (instance == null) {
            synchronized (GenresServiceSingleton.class) {
                if (instance == null) {
                    instance = new GenreService(GenreDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
