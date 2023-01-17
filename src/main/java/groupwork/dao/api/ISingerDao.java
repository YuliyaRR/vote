package groupwork.dao.api;

import groupwork.dto.SingerDTO;

import java.util.List;

public interface ISingerDao {

    List<SingerDTO> getSingerList();

    boolean isContain(int id);

    void delete(SingerDTO singerDTO);

    void create(SingerDTO singerDTO);

    void update(SingerDTO singerDTO);
}
