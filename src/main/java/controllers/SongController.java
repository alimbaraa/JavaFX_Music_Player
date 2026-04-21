package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Song;

import java.util.Objects;

public class SongController {
    @FXML
    private ImageView cover;
    @FXML
    private Label songName;
    @FXML
    private Label artist;
    public void setData(Song song){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(song.getCover())));
        cover.setImage(image);
        songName.setText(song.getName());
        artist.setText(song.getArtist());
    }
}
