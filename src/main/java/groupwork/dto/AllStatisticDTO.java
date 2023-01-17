package groupwork.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AllStatisticDTO {

    private final Map<SingerDTO, Integer> mapSingers;
    private final Map<GenreDTO, Integer> mapGenres;
    private final Map<String, LocalDateTime> mapUserInfo;

    public AllStatisticDTO(Map<SingerDTO, Integer> mapSingers,
                           Map<GenreDTO, Integer> mapGenres,
                           Map<String, LocalDateTime> mapUserInfo) {
        this.mapSingers = mapSingers;
        this.mapGenres = mapGenres;
        this.mapUserInfo = mapUserInfo;
    }

    public Map<SingerDTO, Integer> getMapSingers() {
        return mapSingers;
    }

    public Map<GenreDTO, Integer> getMapGenres() {
        return mapGenres;
    }

    public Map<String, LocalDateTime> getMapUserInfo() {
        return mapUserInfo;
    }


}
