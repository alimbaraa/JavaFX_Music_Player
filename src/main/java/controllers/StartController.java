package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import models.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.SceneController.SPOTIFY;
import static dao.UserDao.ADD_NEW_USER;
import static dao.UserDao.USERS_LIST;

public class StartController implements Initializable {
    @FXML
    private Button logIn, signUp, submitLogIn, submitSignUp;
    @FXML
    private Line logInLine, signUpLine;
    @FXML
    private PasswordField logInPassword, signUpPassword;
    @FXML
    private TextField logInUsername, signUpUsername, signUpGmail;
    @FXML
    private Label logInWarning, signUpWarning;
    ArrayList<User> users;
    String gmail;
    String username;
    String password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logIn.setOnAction(event -> {
            setUpLogIn();
        });
        signUp.setOnAction(event -> {
            setUpSingUp();
        });
    }
    public void setUpLogIn(){
        logInLine.setDisable(false);
        logInLine.setVisible(true);
        logInPassword.setDisable(false);
        logInPassword.setVisible(true);
        logInUsername.setDisable(false);
        logInUsername.setVisible(true);
        submitLogIn.setDisable(false);
        submitLogIn.setVisible(true);
        logInWarning.setDisable(false);
        logInWarning.setVisible(true);
        logInWarning.setText("");

        signUpLine.setDisable(true);
        signUpLine.setVisible(false);
        signUpGmail.setDisable(true);
        signUpGmail.setVisible(false);
        signUpPassword.setDisable(true);
        signUpPassword.setVisible(false);
        signUpUsername.setDisable(true);
        signUpUsername.setVisible(false);
        submitSignUp.setDisable(true);
        submitSignUp.setVisible(false);
        signUpWarning.setDisable(true);
        signUpWarning.setVisible(false);
    }
    public void setUpSingUp(){
        signUpLine.setDisable(false);
        signUpLine.setVisible(true);
        signUpGmail.setDisable(false);
        signUpGmail.setVisible(true);
        signUpPassword.setDisable(false);
        signUpPassword.setVisible(true);
        signUpUsername.setDisable(false);
        signUpUsername.setVisible(true);
        submitSignUp.setDisable(false);
        submitSignUp.setVisible(true);
        signUpWarning.setDisable(false);
        signUpWarning.setVisible(true);
        signUpWarning.setText("");

        logInLine.setDisable(true);
        logInLine.setVisible(false);
        logInPassword.setDisable(true);
        logInPassword.setVisible(false);
        logInUsername.setDisable(true);
        logInUsername.setVisible(false);
        submitLogIn.setDisable(true);
        submitLogIn.setVisible(false);
        logInWarning.setDisable(true);
        logInWarning.setVisible(false);
    }
    public void submitLogIn(ActionEvent event) throws SQLException {
        users = new ArrayList<>(USERS_LIST());
        username = logInUsername.getText();
        password = logInPassword.getText();
        for(int i = 0; i < users.size(); i++){
            String DBUsername = users.get(i).getUsername();
            String DBPassword = users.get(i).getPassword();
            if(username.equals(DBUsername) && password.equals(DBPassword)){
                try {
                    SPOTIFY(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(i == users.size() && !username.equals(DBUsername) || !password.equals(DBPassword)){
                logInWarning.setText("Incorrect username or password");
                logInWarning.setDisable(false);
                logInWarning.setVisible(true);
            }
        }
    }
    public void submitSignUp(ActionEvent event) throws SQLException {
        users = new ArrayList<>(USERS_LIST());
        gmail = signUpGmail.getText();
        username = signUpUsername.getText();
        password = signUpPassword.getText();
        for(int i = 0; i < users.size(); i++){
            String DBGmail = users.get(i).getGmail();
            String DBUsername = users.get(i).getUsername();
            if(gmail.equals(DBGmail)){
                signUpWarning.setText("This gmail is already taken");
                signUpWarning.setDisable(false);
                signUpWarning.setVisible(true);
                break;
            } else if (username.equals(DBUsername)) {
                signUpWarning.setText("this username is already taken");
                signUpWarning.setDisable(false);
                signUpWarning.setVisible(true);
                break;
            } else {
                    try {
                        SPOTIFY(event);
                        ADD_NEW_USER(gmail, username, password);
                        users.add(new User(gmail, username, password));
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
