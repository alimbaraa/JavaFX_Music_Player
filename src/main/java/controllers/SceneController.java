package controllers;

import com.example.spotifyfromwish.StartScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage STAGE = new Stage();
    public static void LOGIN_SIGNUP(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(StartScene.class.getResource("logIn-signUp.fxml"));
        STAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
        STAGE.setTitle("Spotify");
        STAGE.setResizable(false);
        Scene scene = new Scene(parent);
        STAGE.setScene(scene);
        STAGE.show();
    }
    public static void SPOTIFY(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(StartScene.class.getResource("main-page.fxml"));
        STAGE = (Stage) ((Node) event.getSource()).getScene().getWindow();
        STAGE.setTitle("Spotify");
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("style.com.example.spotifyfromwish.css");
        STAGE.setScene(scene);
        STAGE.show();
    }
}
