package groupwork.dao.api;

import groupwork.dto.GenreDTO;

import java.util.List;

public interface IGenreDao {

    List<GenreDTO> getGenreList();

    boolean isContain(int id);

    void delete(GenreDTO genreDTO);

    void create(GenreDTO genreDTO);

    void update(GenreDTO genreDTO);
}
