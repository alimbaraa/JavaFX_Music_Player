package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCon {
    private static final String CREATE_SCHEME_QUERY = "CREATE SCHEMA IF NOT EXISTS SPOTIFY;";
    private static final String URL_FOR_SCHEMA = "jdbc:mysql://localhost:3306";
    private static final String URL = "jdbc:mysql://localhost:3306/spotify";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    public static Connection GET_CONNECTION(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void NEW_SPOTIFY_SCHEMA() throws SQLException {
        Connection connection = DriverManager.getConnection(URL_FOR_SCHEMA, USER, PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_SCHEME_QUERY);
        statement.close();
        connection.close();
    }
}
