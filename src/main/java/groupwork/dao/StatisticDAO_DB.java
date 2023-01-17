package groupwork.dao;

import groupwork.dao.api.IStatisticDAO_DB;
import groupwork.dto.GenreDTO;
import groupwork.dto.SingerDTO;
import groupwork.helper.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class StatisticDAO_DB implements IStatisticDAO_DB {

    @Override
    public Map<SingerDTO, Integer> getSingerVote() {
        String sqlSingerVote = "SELECT app.artists.id, app.artists.name, COUNT(id_artist) as count_voice " +
                "FROM app.votes  " +
                "INNER JOIN app.vote_artist ON app.votes.id = app.vote_artist.id_vote " +
                "RIGHT JOIN app.artists ON app.vote_artist.id_artist = app.artists.id " +
                "GROUP BY app.artists.id, app.artists.name " +
                "ORDER BY count_voice DESC;";
        Map<SingerDTO, Integer> map = new LinkedHashMap<>();

        try(Connection connection = Provider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSingerVote);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int count = resultSet.getInt("count_voice");
                map.put(new SingerDTO(name, id), count);
            }

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }

        return map;

    }

    @Override
    public Map<GenreDTO, Integer> getGenreVote() {
        String sqlGenreVote = "SELECT app.genres.id, app.genres.name, COUNT(app.genres.id) as count_voice " +
                "FROM app.votes " +
                "INNER JOIN app.vote_genre ON app.votes.id = app.vote_genre.id_vote " +
                "RIGHT JOIN app.genres ON app.vote_genre.id_genre = app.genres.id " +
                "GROUP BY app.genres.id, app.genres.name " +
                "ORDER BY count_voice DESC;";

        Map<GenreDTO, Integer> map = new LinkedHashMap<>();

        try(Connection connection = Provider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGenreVote);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int count = resultSet.getInt("count_voice");
                map.put(new GenreDTO(name, id), count);
            }

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }

        return map;
    }

    @Override
    public Map<String, LocalDateTime> getAboutVote() {
        String sqlAbout = "SELECT dt_create, about " +
                "FROM app.votes " +
                "ORDER BY dt_create DESC;";
        Map<String, LocalDateTime> map = new LinkedHashMap<>();

        try(Connection connection = Provider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlAbout);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                String about = resultSet.getString("about");
                LocalDateTime dtCreate = resultSet.getObject("dt_create", LocalDateTime.class);
                map.put(about, dtCreate);
            }

        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с базой данных");
        }

        return map;

    }
}
