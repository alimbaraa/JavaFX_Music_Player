package dao;

import models.Song;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static dao.DBCon.GET_CONNECTION;

public class SongDao {
    private static final StringBuilder CREATE_SONGS_TABLE_QUERY = new StringBuilder("CREATE TABLE IF NOT EXISTS SONGS (");
    private static final String GET_SONGS_FROM_TABLE_QUERY = "SELECT * FROM SPOTIFY.SONGS";
    static List<Song> SONGS;
    {
        CREATE_SONGS_TABLE_QUERY.append("COVER VARCHAR(90), ");
        CREATE_SONGS_TABLE_QUERY.append("NAME VARCHAR(90) PRIMARY KEY, ");
        CREATE_SONGS_TABLE_QUERY.append("ARTIST VARCHAR(90), ");
        CREATE_SONGS_TABLE_QUERY.append("MP3 VARCHAR(90), ");
        CREATE_SONGS_TABLE_QUERY.append("LENGTH VARCHAR(90), ");
        CREATE_SONGS_TABLE_QUERY.append("FAVOURITE VARCHAR(90));");
    }
    public static void CREATE_SONGS_TABLE() throws SQLException {
        Statement statement = GET_CONNECTION().createStatement();
        statement.executeUpdate(String.valueOf(CREATE_SONGS_TABLE_QUERY));
        statement.close();
    }
    public static void ADD_SONGS_IN_TABLE() throws SQLException {
        SONGS = new ArrayList<>(GET_SONGS());

        for(int i = 0; i < SONGS.size(); i++) {
            String cover = SONGS.get(i).getCover();
            String name = SONGS.get(i).getName();
            String artist = SONGS.get(i).getArtist();
            String MP3 = SONGS.get(i).getMP3();
            String length = SONGS.get(i).getLength();
            String favourite = String.valueOf(SONGS.get(i).getLiked());
            String newSongQuery = "INSERT INTO SPOTIFY.SONGS VALUES (?,?,?,?,?,?);";
            String query = "SELECT * FROM SPOTIFY.USERS";

            Statement statement = GET_CONNECTION().createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                PreparedStatement preparedStatement = GET_CONNECTION().prepareStatement(newSongQuery);
                preparedStatement.setString(1, cover);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, artist);
                preparedStatement.setString(4, MP3);
                preparedStatement.setString(5, length);
                preparedStatement.setString(6, favourite);

                preparedStatement.executeUpdate();
                preparedStatement.close();
            } else {
                continue;
            }
        }
    }
    public static List<Song> SONGS_LIST() throws SQLException {
        List<Song> list = new ArrayList<>();

        Statement statement = GET_CONNECTION().createStatement();
        ResultSet rs = statement.executeQuery(GET_SONGS_FROM_TABLE_QUERY);
        while (rs.next()){
            list.add(new Song(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Boolean.parseBoolean(rs.getString(6))));
        }
        rs.close();
        statement.close();
        return list;
    }
    public static List<Song> GET_SONGS(){
        List<Song> list = new ArrayList<>();

        Song song = new Song(null, null, null, null, null, null);
        song.setName("In The Name Of Love");
        song.setArtist("Martix Garrix, Bebe Rexha");
        song.setCover("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\In_the_Name_of_Love.jpeg");
        song.setMP3("/songs/In The Name Of Love.mp3");
        song.setLength("03:25");
        song.setLiked(false);
        list.add(song);

        song = new Song(null, null, null, null, null, null);
        song.setName("It Ain't Me");
        song.setArtist("Selena gomez, Kygo");
        song.setCover("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\It-Ain-t-Me.jpg");
        song.setMP3("/songs/It Aint Me.mp3.mp3");
        song.setLength("03:41");
        song.setLiked(false);
        list.add(song);

        song = new Song(null, null, null, null, null, null);
        song.setName("On My Way");
        song.setArtist("Alan Walker");
        song.setCover("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\on_my_way.jpg");
        song.setMP3("/songs/On My Way.mp3.mp3");
        song.setLength("03:36");
        song.setLiked(false);
        list.add(song);

        song = new Song(null, null, null, null, null, null);
        song.setName("Can't Help Falling In Love");
        song.setArtist("Elvis Presley");
        song.setCover("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\cant_help_falling_in_love.jpg");
        song.setMP3("/songs/Can't Help Falling In Love.mp3");
        song.setLength("03:00");
        song.setLiked(false);
        list.add(song);

        song = new Song(null, null, null, null, null, null);
        song.setName("No Time To Die");
        song.setArtist("Billie Eilish");
        song.setCover("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\no_time_to_die.jpg");
        song.setMP3("/songs/No Time To Die.mp3.mp3");
        song.setLength("03:59");
        song.setLiked(false);
        list.add(song);

        song = new Song(null, null, null, null, null, null);
        song.setName("One Dance");
        song.setArtist("Drake");
        song.setCover("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\one_dance.jpg");
        song.setMP3("/songs/One Dance Lyrics.mp3.mp3");
        song.setLength("02:55");
        song.setLiked(false);
        list.add(song);

        song = new Song(null, null, null, null, null, null);
        song.setName("Faded");
        song.setArtist("Alan Walker");
        song.setCover("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\faded.jpg");
        song.setMP3("/songs/Faded.mp3.mp3");
        song.setLength("03:32");
        song.setLiked(false);
        list.add(song);

        return list;
    }
    public static void LIKED_SONG(String songName) throws SQLException {
        String query = "update spotify.songs set FAVOURITE = 'true' where name =  \"" + songName +"\";";
        Statement statement = GET_CONNECTION().createStatement();
        statement.executeUpdate(query);
        statement.close();
    }
    public static void UNLIKED_SONG(String songName) throws SQLException {
        String query = "UPDATE SPOTIFY.SONGS SET FAVOURITE = 'false' WHERE NAME = \"" + songName + "\";";
        Statement statement = GET_CONNECTION().createStatement();
        statement.executeUpdate(query);
        statement.close();
    }
}
