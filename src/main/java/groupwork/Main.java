package groupwork;

import groupwork.dto.GenreDTO;
import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.SingerDTO;
import groupwork.dto.VoiceDTO;
import groupwork.helper.Provider;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

       /* try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

//delete
        int id = 3;

        try (Connection connection = Provider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM app.artists WHERE id = ?;")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }




//отсортированнная инфа о себе
        /*String sqlAbout = "SELECT dt_create, about " +
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
            e.printStackTrace();
        }

        for (Map.Entry<String, LocalDateTime> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }*/

//статистика по жанрам
        /*String sqlGenreVote = "SELECT app.genres.id, app.genres.name, COUNT(app.genres.id) as count_voice " +
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
            e.printStackTrace();
        }

        for (Map.Entry<GenreDTO, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
*/


//статистика по голосам
        /*String sqlSingerVote = "SELECT app.artists.id, app.artists.name, COUNT(id_artist) as count_voice " +
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
            e.printStackTrace();
        }

        for (Map.Entry<SingerDTO, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }*/


 //getAllVoice
        /*List<SavedVoiceDTO> list = new ArrayList<>();

        String sql = "SELECT app.votes.id, dt_create, about, id_artist, id_genre " +
                "FROM app.votes " +
                "INNER JOIN app.vote_artist ON app.votes.id = app.vote_artist.id_vote " +
                "INNER JOIN app.vote_genre ON app.votes.id = app.vote_genre.id_vote;";

        try(Connection connection = Provider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                List<Integer> genreList = new ArrayList<>();
                int currId = resultSet.getInt("id");
                LocalDateTime dtCreate = resultSet.getObject("dt_create", LocalDateTime.class);
                String about = resultSet.getString("about");
                int singer = resultSet.getInt("id_artist");
                int genre = resultSet.getInt("id_genre");
                genreList.add(genre);

                while (resultSet.next()){
                    if(resultSet.getInt("id") == currId) {
                        genreList.add(resultSet.getInt("id_genre"));
                    } else {
                        resultSet.previous();
                        break;
                    }
                }

                int[]genres = genreList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();

                SavedVoiceDTO voice = new SavedVoiceDTO(
                        new VoiceDTO(singer,genres, about),
                        dtCreate);

                list.add(voice);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        for (SavedVoiceDTO voiceDTO : list) {
            System.out.println(voiceDTO);
        }*/


//сохранение голоса
       /* String sglInsertVoice = "INSERT INTO app.votes (dt_create, about) VALUES (?,?) RETURNING id;";
        String sqlInsertSinger = "INSERT INTO app.vote_artist (id_vote, id_artist) VALUES (?, ?);";
        String sqlInsertGenre = "INSERT INTO app.vote_genre (id_vote, id_genre) VALUES (?, ?);";

        int [] arrGenre = {7, 5, 6};
        int singer = 6;
        SavedVoiceDTO voice = new SavedVoiceDTO(new VoiceDTO(singer, arrGenre, "about"));
        LocalDateTime creationTime = voice.getCreationTime();
        String message = "about";
        int id = 0;

        try(Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/voting",
                "postgres", "789qaz")
            //PreparedStatement preparedStatement = connection.prepareStatement(sglInsertVoice);
           //PreparedStatement preparedStatement2 = connection.prepareStatement(sqlInsertSinger);
           // PreparedStatement preparedStatement3 = connection.prepareStatement(sqlInsertGenre)){

            PreparedStatement preparedStatement = connection.prepareStatement(sglInsertVoice);

            preparedStatement.setObject(1, creationTime);
            preparedStatement.setString(2, message);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt("id");
            }

            preparedStatement = connection.prepareStatement(sqlInsertSinger);

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, singer);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlInsertGenre);

            for (int i : arrGenre) {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e){
            e.printStackTrace();
        }*/

    }
}
