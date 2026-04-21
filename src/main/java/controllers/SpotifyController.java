package controllers;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import models.Song;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static controllers.SceneController.LOGIN_SIGNUP;
import static dao.SongDao.*;

public class SpotifyController implements Initializable {
    @FXML
    private HBox recentlyPlayedHBox, favoutireHBox, homeHBox, searchHBox, searchingHBox;
    @FXML
    private Button nextSongButton, pauseOrPlayButton, previousSongButton, homeButton, searchButton, likeButton;
    @FXML
    private Label currentSongName, currentSongArtist, currentTime, songLength;
    @FXML
    private ImageView currentSongCover, playOrPauseImage, likedImage;
    @FXML
    private ProgressBar songProgressBar;
    private Media media;
    private MediaPlayer mediaPlayer;
    private ArrayList<Song> songs;
    private int songIndex, skipCount;
    private Timer timer;
    private TimerTask task;
    private boolean running;
    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            //pause or play button
            if (!running) {
                pauseOrPlayButton.setOnAction(event -> {
                    playSong();
                });
            } else {
                pauseOrPlayButton.setOnAction(event -> {
                    pauseSong();
                });
            }
            //like button
            if(songs.get(songIndex).getLiked()){
                likedImage.setImage(new Image("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\filledHeart.png"));
                likeButton.setOnAction(event -> {
                    try {
                        UNLIKED_SONG(songs.get(songIndex).getName());
                        songs.get(songIndex).setLiked(false);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                likedImage.setImage(new Image("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\favorite.png"));
                likeButton.setOnAction(event -> {
                    try {
                        LIKED_SONG(songs.get(songIndex).getName());
                        songs.get(songIndex).setLiked(true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
            //to fill favorite songs VBOX
        }
    };
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                songs = new ArrayList<>(SONGS_LIST());
                running = false;
                songIndex = 0;
                skipCount = 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //animator
            animationTimer.start();

            //media
            String URL = songs.get(songIndex).getMP3();
            String name = songs.get(songIndex).getName();
            String artist = songs.get(songIndex).getArtist();
            String cover = songs.get(songIndex).getCover();

            try {
                media = new Media(getClass().getResource(URL).toURI().toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            mediaPlayer = new MediaPlayer(media);
            currentSongName.setText(name);
            currentSongArtist.setText(artist);
            currentSongCover.setImage(new Image(cover));

            System.out.println(URL);

            //actions on buttons
            searchButton.setOnAction(event -> {
                homeHBox.setStyle("null");
                searchHBox.setStyle("-fx-border-color: #1ED760;\n" +
                        "   -fx-border-width: 0 0 0 5;");
                searchingHBox.setDisable(false);
                searchingHBox.setVisible(true);
            });
            homeButton.setOnAction(event -> {
                searchHBox.setStyle("null");
                homeHBox.setStyle("-fx-border-color: #1ED760;\n" +
                        "   -fx-border-width: 0 0 0 5;");
                searchingHBox.setDisable(true);
                searchingHBox.setVisible(false);
            });
        }
        public void logOut(ActionEvent event) throws IOException {
            LOGIN_SIGNUP(event);
        }
        public void search() {
            //search
        }
        public void playSong() {
            beginTimer();
            mediaPlayer.play();
            playOrPauseImage.setImage(new Image("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\pause.png"));
            running = true;
        }
        public void pauseSong() {
            cancelTimer();
            mediaPlayer.pause();
            playOrPauseImage.setImage(new Image("C:\\Users\\PC\\IdeaProjects\\SpotifyFromWish\\src\\main\\resources\\images\\play.png"));
            running = false;
        }
        public void previousSong() throws URISyntaxException {
            if (songIndex > 0) {
                songIndex--;
                skipCount++;
                mediaPlayer.stop();

                if(running){
                    timer.cancel();
                }

                media = new Media(getClass().getResource(songs.get(songIndex).getMP3()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                currentSongName.setText(songs.get(songIndex).getName());
                currentSongArtist.setText(songs.get(songIndex).getArtist());
                currentSongCover.setImage(new Image(songs.get(songIndex).getCover()));
                songLength.setText(songs.get(songIndex).getLength());
                playSong();
            } else {
                songIndex = songs.size() - 1;
                skipCount++;
                mediaPlayer.stop();

                if(running){
                    timer.cancel();
                }

                media = new Media(getClass().getResource(songs.get(songIndex).getMP3()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                currentSongName.setText(songs.get(songIndex).getName());
                currentSongArtist.setText(songs.get(songIndex).getArtist());
                currentSongCover.setImage(new Image(songs.get(songIndex).getCover()));
                songLength.setText(songs.get(songIndex).getLength());
                playSong();
            }
        }
        public void nextSong() throws URISyntaxException {
            if (songIndex < songs.size() - 1) {
                songIndex++;
                skipCount++;
                mediaPlayer.stop();

                if(running){
                    timer.cancel();
                }

                media = new Media(getClass().getResource(songs.get(songIndex).getMP3()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                currentSongName.setText(songs.get(songIndex).getName());
                currentSongArtist.setText(songs.get(songIndex).getArtist());
                currentSongCover.setImage(new Image(songs.get(songIndex).getCover()));
                songLength.setText(songs.get(songIndex).getLength());
                playSong();
            } else {
                songIndex = 0;
                skipCount++;
                mediaPlayer.stop();

                if(running){
                    timer.cancel();
                }

                media = new Media(getClass().getResource(songs.get(songIndex).getMP3()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                currentSongName.setText(songs.get(songIndex).getName());
                currentSongArtist.setText(songs.get(songIndex).getArtist());
                currentSongCover.setImage(new Image(songs.get(songIndex).getCover()));
                songLength.setText(songs.get(songIndex).getLength());
                playSong();
            }
        }
        public void beginTimer() {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    double current = mediaPlayer.getCurrentTime().toSeconds();
                    double length = media.getDuration().toSeconds();

                    songProgressBar.setProgress(current/length);
                    //currentTime.setText(String.valueOf(current));

                    if(current/length == 1){
                        cancelTimer();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 1000, 1000);
        }
        public void cancelTimer() {
            timer.cancel();
        }
        public void onUpgradeButton(ActionEvent event){

        }
        public void likedSongsButton(){

        }
    }