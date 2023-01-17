package groupwork.dao;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class SingerDao implements ISingerDao {
    private Map<Integer, SingerDTO> singers = new ConcurrentHashMap<>();

    {
        singers.put(1, new SingerDTO("Sting", 1));
        singers.put(2, new SingerDTO("Scorpions", 2));
        singers.put(3, new SingerDTO("Madonna", 3));
        singers.put(4, new SingerDTO("GreenDay", 4));
    }

    @Override
    public List<SingerDTO> getSingerList() {
        return new ArrayList<>(singers.values());
    }


    @Override
    public boolean isContain(int id) {
        return singers.containsKey(id);
    }

    @Override
    public void delete(SingerDTO singerDTO) {
        int id = singerDTO.getId();
        singers.remove(id);
    }

    @Override
    public void create(SingerDTO singerDTO) {
        String name = singerDTO.getName();
        if (checkDuplicate(name)) {
            int id = getMaxID();
            singerDTO.setId(id);
            singers.put(id, singerDTO);
        } else {
            throw new IllegalArgumentException("Такой жанр уже существует");
        }
    }

    @Override
    public void update(SingerDTO singerDTO) {
        String name = singerDTO.getName();
        if (checkDuplicate(name)) {
            singers.put(singerDTO.getId(), singerDTO);
        } else {
            throw new IllegalArgumentException("Такой жанр уже существует");
        }
    }

    private synchronized int getMaxID(){
        int currID = singers.keySet().stream()
                .max(Comparator.comparing(Integer::intValue))
                .get();
        return ++currID;
    }

    private synchronized boolean checkDuplicate(String name) {
        return singers.values().stream()
                .map(singerDTO -> singerDTO.getName())
                .noneMatch(s -> s.equals(name));
    }
}
