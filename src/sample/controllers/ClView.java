package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class ClView {

    @FXML
    private MediaView mediaView;

    @FXML
    void initialize() {

        String path = "file:/D:/Roma/University/III_year_I_part/CG/Project/src/sample/resources/ColorScheme.mp4";
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

    }
}
