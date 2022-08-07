package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelpController {

    @FXML
    private Button goToPracticeButton;

    @FXML
    private Button nextStepButton;

    @FXML
    private Button homeButtonId;

    @FXML
    private Button colorLearningButton;

    @FXML
    private Button paralellogramLearningButton;

    @FXML
    private Button previousStepButton;

    @FXML
    void initialize() {

        previousStepButton.setDisable(true);

        goToPracticeButton.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/FractVideo.fxml"));
                Stage stage = (Stage) goToPracticeButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Video");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        nextStepButton.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/colorHelp.fxml"));
                Stage stage = (Stage) nextStepButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Computer Graphic");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        homeButtonId.setOnAction(actionEvent -> {
//            try {

//                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/homePage.fxml"));
                Stage stage = (Stage) homeButtonId.getScene().getWindow();
//                stage.setScene(new Scene(root));
//                stage.setTitle("Computer Graphic");
                stage.hide();

//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        });

        colorLearningButton.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/colorHelp.fxml"));
                Stage stage = (Stage) colorLearningButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Computer Graphic");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        paralellogramLearningButton.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/affineHelp.fxml"));
                Stage stage = (Stage) paralellogramLearningButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Computer Graphic");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

}

