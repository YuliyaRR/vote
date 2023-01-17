package groupwork.dao.api;

import groupwork.dto.GenreDTO;
import groupwork.dto.SingerDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface IStatisticDAO_DB {

    Map<SingerDTO, Integer> getSingerVote();

    Map<GenreDTO, Integer> getGenreVote();

    Map<String, LocalDateTime> getAboutVote();


}
