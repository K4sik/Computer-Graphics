package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FractalLearningController {

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

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/fractalPage.fxml"));
                Stage stage = (Stage) goToPracticeButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Fractal");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        nextStepButton.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/frLearnPage2.fxml"));
                Stage stage = (Stage) nextStepButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Color Learning");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        colorLearningButton.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/colorLearningPage.fxml"));
                Stage stage = (Stage) colorLearningButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Color Learning");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        paralellogramLearningButton.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/parallelpgramLearningPage.fxml"));
                Stage stage = (Stage) paralellogramLearningButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Parallelogram");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}
