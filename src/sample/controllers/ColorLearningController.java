package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class ColorLearningController {

    @FXML
    private Button homeButtonId;

    @FXML
    private Button fractalLearningButton;

    @FXML
    private Button paralellogramLearningButton;

    @FXML
    private Button goToPracticeButton;

    @FXML
    private Button nextStepButton;

    @FXML
    private Button previousStepButton;

    @FXML
    private TextArea textArea;

    @FXML
    void initialize() {

        textArea.setEditable(false);

        homeButtonId.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/homePage.fxml"));
                Stage stage = (Stage) homeButtonId.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Computer Graphic");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        goToPracticeButton.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/colorSchemePage.fxml"));
                Stage stage = (Stage) goToPracticeButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Color Scheme");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        previousStepButton.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/fractalLearningPage.fxml"));
                Stage stage = (Stage) previousStepButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Learning Fractal");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        nextStepButton.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/parallelpgramLearningPage.fxml"));
                Stage stage = (Stage) nextStepButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Learning Parallelogram");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        fractalLearningButton.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/fractalLearningPage.fxml"));
                Stage stage = (Stage) fractalLearningButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Learning Fractal");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        paralellogramLearningButton.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/parallelpgramLearningPage.fxml"));
                Stage stage = (Stage) paralellogramLearningButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Learning Parallelogram");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}
