package groupwork.dao;

import groupwork.dao.api.IGenreDao;
import groupwork.dto.GenreDTO;
import groupwork.helper.Provider;
import groupwork.helper.factory.ConnectionPoolSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO_DB implements IGenreDao {

    private final String SQL_GET = "SELECT id, name FROM app.genres;";
    private final String SQL_IS_CONTAIN = "SELECT id FROM app.genres WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM app.genres WHERE id = ?;";
    private final String SQL_CREATE = "INSERT INTO app.genres (name) VALUES (?);";
    private final String SQL_UPDATE = "UPDATE app.genres SET name = ? WHERE id = ?;";
    @Override
    public List<GenreDTO> getGenreList() {
        List<GenreDTO>list = new ArrayList<>();

        try(Connection connection = ConnectionPoolSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new GenreDTO(name, id));
            }

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }

        return list;
    }

    @Override
    public boolean isContain(int id) {
        boolean result = false;

        try(Connection connection = ConnectionPoolSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_CONTAIN)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                result = true;
            }

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }
        return result;
    }

    @Override
    public void delete(GenreDTO genreDTO) {
        int id = genreDTO.getId();

        try (Connection connection = ConnectionPoolSingleton.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }
    }

    @Override
    public void create(GenreDTO genreDTO) {
        String genre = genreDTO.getName();

        try(Connection connection = ConnectionPoolSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)){

            preparedStatement.setString(1, genre);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }
    }

    @Override
    public void update(GenreDTO genreDTO) {
        int id = genreDTO.getId();
        String genre = genreDTO.getName();

        try(Connection connection = ConnectionPoolSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){

            preparedStatement.setString(1, genre);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }

    }
}
