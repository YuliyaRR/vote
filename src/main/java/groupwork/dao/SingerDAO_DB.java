package groupwork.dao;

import groupwork.dao.api.ISingerDao;
import groupwork.dto.SingerDTO;
import groupwork.helper.Provider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SingerDAO_DB implements ISingerDao {
    private final String SQL_GET = "SELECT id, name FROM app.artists;";
    private final String SQL_IS_CONTAIN = "SELECT id FROM app.artists WHERE id = ?;";
    private final String SQL_DELETE = "DELETE FROM app.artists WHERE id = ?;";
    private final String SQL_CREATE = "INSERT INTO app.artists (name) VALUES (?);";
    private final String SQL_UPDATE = "UPDATE app.artists SET name = ? WHERE id = ?;";


    @Override
    public List<SingerDTO> getSingerList() {
        List<SingerDTO>list = new ArrayList<>();

        try(Connection connection = Provider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new SingerDTO(name, id));
            }

        } catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }

        return list;
    }

    @Override
    public boolean isContain(int id) {
        boolean result = false;

        try(Connection connection = Provider.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_IS_CONTAIN)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                result = true;
            }

            resultSet.close();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }
        return result;
    }

    @Override
    public void delete(SingerDTO singerDTO) {
        int id = singerDTO.getId();

        try (Connection connection = Provider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }
    }

    @Override
    public void create(SingerDTO singerDTO) {
        String singer = singerDTO.getName();

        try(Connection connection = Provider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)){

            preparedStatement.setString(1, singer);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }
    }

    @Override
    public void update(SingerDTO singerDTO) {
        int id = singerDTO.getId();
        String singer = singerDTO.getName();

        try(Connection connection = Provider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){

            preparedStatement.setString(1, singer);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }

    }
}
