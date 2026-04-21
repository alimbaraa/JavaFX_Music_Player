package com.example.spotifyfromwish;

import dao.SongDao;
import dao.UserDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static dao.DBCon.NEW_SPOTIFY_SCHEMA;

public class StartScene extends Application {
    private UserDao userDao;
    private SongDao songDao;

    public StartScene() {
        this.userDao = new UserDao();
        this.songDao = new SongDao();
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Parent parent = FXMLLoader.load(StartScene.class.getResource("logIn-signUp.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Spotify");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        //SQL. UNCOMMENT COMMENTED METHODS FOR SQL
        //NEW_SPOTIFY_SCHEMA();
        //userDao.CREATE_USERS_TABLE();
        //songDao.CREATE_SONGS_TABLE();
        //songDao.ADD_SONGS_IN_TABLE();
    }

    public static void main(String[] args) {
        launch();
    }
}