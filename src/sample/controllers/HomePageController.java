package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPaneid;

    @FXML
    private ImageView fractalImg;

    @FXML
    private ImageView colorImg;

    @FXML
    private ImageView parallelogramImg;

    @FXML
    private Button fractalButtonid;

    @FXML
    private Button colorSchemeButtonid;

    @FXML
    private Button parallelogramButtonid;

    @FXML
    private Button learningButton;

    @FXML
    void initialize() {

        fractalButtonid.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/fractalPage.fxml"));
                Stage stage = (Stage) fractalButtonid.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Fractal");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        colorSchemeButtonid.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/colorSchemePage.fxml"));
                Stage stage = (Stage) colorSchemeButtonid.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Color Scheme");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        parallelogramButtonid.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/parallelpgramPage.fxml"));
                Stage stage = (Stage) parallelogramButtonid.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Parallelogram");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        learningButton.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/fractalLearningPage.fxml"));
                Stage stage = (Stage) parallelogramButtonid.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Learning Fractal");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static void showAlert(String simpleName, String message, Alert.AlertType error) {

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Помилка");
        errorAlert.setHeaderText(simpleName);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();

    }
}
