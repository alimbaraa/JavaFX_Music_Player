package dao;

import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static dao.DBCon.GET_CONNECTION;

public class UserDao {
    private static final StringBuilder CREATE_USERS_TABLE_QUERY = new StringBuilder("CREATE TABLE IF NOT EXISTS USERS (");
    private static final String ADD_NEW_USER_QUERY = "INSERT INTO SPOTIFY.USERS VALUES (?,?,?);";
    {
        CREATE_USERS_TABLE_QUERY.append("EMAIL VARCHAR(90) PRIMARY KEY, ");
        CREATE_USERS_TABLE_QUERY.append("USERNAME VARCHAR(90), ");
        CREATE_USERS_TABLE_QUERY.append("PASSWORD VARCHAR(90));");
    }
    public static void CREATE_USERS_TABLE() throws SQLException {
        Statement statement = GET_CONNECTION().createStatement();
        statement.executeUpdate(String.valueOf(CREATE_USERS_TABLE_QUERY));
        statement.close();
    }
    public static void ADD_NEW_USER(String gmail, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = GET_CONNECTION().prepareStatement(ADD_NEW_USER_QUERY);
        preparedStatement.setString(1, gmail);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public static ArrayList<User> USERS_LIST() throws SQLException {
        ArrayList<User> arrayList = new ArrayList<>();

        final String query = "SELECT * FROM SPOTIFY.USERS";
        Statement statement = GET_CONNECTION().createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            arrayList.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        statement.close();

        return arrayList;
    }
}
