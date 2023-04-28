package groupwork.service;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;
import groupwork.service.api.IGenreService;


import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao dao;

    public GenreService(IGenreDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean check(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Введите id жанра");
        }
        return this.dao.isContain(number);

    }

    @Override
    public List<GenreDTO> get() {
        return dao.getGenreList();
    }

    @Override
    public void delete(GenreDTO genreDTO) {
        int id = genreDTO.getId();

        if (id == 0) {
            throw new IllegalArgumentException("Введите номер жанра");
        }

        if(dao.isContain(id)){
            dao.delete(genreDTO);
        } else {
            throw new IllegalArgumentException("Нет жанра для удаления с таким id");
        }
    }

    @Override
    public void create(GenreDTO genreDTO) {
        String genre = genreDTO.getName();
        if(genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Не введен жанр");
        }

        dao.create(genreDTO);

    }

    @Override
    public void update(GenreDTO genreDTO) {
        String genre = genreDTO.getName();

        if(genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Не введен жанр");
        }

        int id = genreDTO.getId();

        if (id == 0) {
            throw new IllegalArgumentException("Введите id жанра");
        }

        if(dao.isContain(id)){
            dao.update(genreDTO);
        } else {
            throw new IllegalArgumentException("Нет жанра для обновления с таким id");
        }

    }
    @Override
    public GenreDTO getGenre(int id) {
        return this.dao.getGenre(id);
    }
}
