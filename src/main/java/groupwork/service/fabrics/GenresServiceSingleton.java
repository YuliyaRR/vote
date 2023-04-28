package groupwork.service.fabrics;

import groupwork.dao.provider.ChoiceDaoProvider;
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
                    instance = new GenreService(ChoiceDaoProvider.getInstance().genreDao());
                }
            }
        }
        return instance;
    }
}
