package groupwork.dto;

import java.util.Objects;

public class SingerDTO {
    private String name;
    private int id;

    public SingerDTO(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public SingerDTO(String name) {
        this.name = name;
    }

    public SingerDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingerDTO singerDTO = (SingerDTO) o;
        return id == singerDTO.id && Objects.equals(name, singerDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "id = " + id + ",  name = " + name;
    }
}
