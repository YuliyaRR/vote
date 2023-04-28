package groupwork.dao.db;

import groupwork.dao.api.ISingerDao;
import groupwork.dao.db.ds.api.IDataSourceWrapper;
import groupwork.dto.SingerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SingerDAO_DB implements ISingerDao {

    private final IDataSourceWrapper dataSource;
    private final String SQL_GET = "SELECT id, name FROM app.artists;";
    private final String SQL_IS_CONTAIN = "SELECT id FROM app.artists WHERE id = ?;";
    private final String SQL_DELETE = "DELETE FROM app.artists WHERE id = ?;";
    private final String SQL_CREATE = "INSERT INTO app.artists (name) VALUES (?);";
    private final String SQL_UPDATE = "UPDATE app.artists SET name = ? WHERE id = ?;";

    public SingerDAO_DB(IDataSourceWrapper dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<SingerDTO> getSingerList() {
        List<SingerDTO>list = new ArrayList<>();

        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new SingerDTO(name, id));
            }

        } catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных", e);
        }

        return list;
    }

    @Override
    public boolean isContain(int id) {
        boolean result = false;

        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_IS_CONTAIN)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                result = true;
            }

            resultSet.close();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных", e);
        }
        return result;
    }

    @Override
    public void delete(SingerDTO singerDTO) {
        int id = singerDTO.getId();

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных", e);
        }
    }

    @Override
    public void create(SingerDTO singerDTO) {
        String singer = singerDTO.getName();

        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)){

            preparedStatement.setString(1, singer);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных", e);
        }
    }

    @Override
    public void update(SingerDTO singerDTO) {
        int id = singerDTO.getId();
        String singer = singerDTO.getName();

        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){

            preparedStatement.setString(1, singer);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных", e);
        }
    }

    @Override
    public SingerDTO getSinger(int id) {
        String sql = "SELECT name FROM app.artists WHERE id = ?";
        String singer = "";
        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                singer = resultSet.getString("name");
            }

            return new SingerDTO(singer, id);

        } catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных", e);
        }
    }
}
