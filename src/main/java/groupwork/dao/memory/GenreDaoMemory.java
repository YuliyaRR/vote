package groupwork.dao.memory;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenreDao implements IGenreDao {

    private Map<Integer, GenreDTO> genres = new ConcurrentHashMap<>();

    {
        genres.put(1, new GenreDTO("pop", 1));
        genres.put(2, new GenreDTO("rock", 2));
        genres.put(3, new GenreDTO("alternative", 3));
        genres.put(4, new GenreDTO("folk", 4));
        genres.put(5, new GenreDTO("bluez", 5));
        genres.put(6, new GenreDTO("pop-rock", 6));
        genres.put(7, new GenreDTO("jazz", 7));
        genres.put(8, new GenreDTO("classic", 8));
        genres.put(9, new GenreDTO("electro", 9));
        genres.put(10,new GenreDTO("cantry", 10));
    }

    @Override
    public List<GenreDTO> getGenreList() {
        return new ArrayList<>(genres.values());
    }

    @Override
    public boolean isContain(int id) {
        return genres.containsKey(id);
    }

    @Override
    public void delete(GenreDTO genreDTO) {
        int id = genreDTO.getId();
        genres.remove(id);
    }

    @Override
    public void create(GenreDTO genreDTO) {
        String name = genreDTO.getName();
        if (checkDuplicate(name)) {
            int id = getMaxID();
            genreDTO.setId(id);
            genres.put(id, genreDTO);
        } else {
            throw new IllegalArgumentException("Такой жанр уже существует");
        }
    }

    @Override
    public void update(GenreDTO genreDTO) {
        String name = genreDTO.getName();
        if (checkDuplicate(name)) {
            genres.put(genreDTO.getId(), genreDTO);
        } else {
            throw new IllegalArgumentException("Такой жанр уже существует");
        }

    }

    private synchronized int getMaxID(){
        int currID = genres.keySet().stream()
                .max(Comparator.comparing(Integer::intValue))
                .get();
        return ++currID;
    }

    private synchronized boolean checkDuplicate(String name) {
        return genres.values().stream()
                .map(genreDTO -> genreDTO.getName())
                .noneMatch(s -> s.equals(name));
    }
}
