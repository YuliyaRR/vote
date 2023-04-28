package groupwork.service.api;

import groupwork.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    boolean check(int number);

    List<GenreDTO> get();

    void delete(GenreDTO genreDTO);

    void create(GenreDTO genreDTO);

    void update(GenreDTO genreDTO);

    GenreDTO getGenre(int id);

}
