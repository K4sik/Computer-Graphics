package sample.controllers;

import java.io.File;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class FractalController {

    @FXML
    private AnchorPane anchorPaneid;

    @FXML
    private Canvas canvasId;

    @FXML
    private ComboBox<?> comboBox;

    @FXML
    private TextField countOfIterationTextField;

    @FXML
    private Button homeButtonId;

    @FXML
    private Button colorSchemeButtonId;

    @FXML
    private Button parallelogramButtonId;

    @FXML
    private Button helpButtonId;

    @FXML
    private Button startButtonId;

    @FXML
    private Button saveButtonId;

    @FXML
    private TextField first;

    @FXML
    private TextField second;

    @FXML
    private CheckBox checkBoxId;

    @FXML
    private CheckBox changeRatio;

    @FXML
    private Line slashLineId;

    @FXML
    private Text ratioText;

    @FXML
    private Label dropmenuhover;

    @FXML
    private Label counthover;

    @FXML
    private Label secondhover;

    @FXML
    private Label firsthover;

    @FXML
    private Button learningButton;

    @FXML
    void initialize() {

        first.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    first.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        countOfIterationTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    countOfIterationTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        changeRatio.setOnAction(actionEvent -> {
            if (changeRatio.isSelected()){
                first.setVisible(false);
                slashLineId.setVisible(false);
                second.setLayoutX(244);
                ratioText.setLayoutX(200);
                second.setMinWidth(45);
                second.setText("");
                secondhover.setText("Enter number between 0.2-1");
            } else {
                first.setVisible(true);
                slashLineId.setVisible(true);
                second.setLayoutX(268);
                ratioText.setLayoutX(172);
                second.setMinWidth(35);
                first.setText("");
                second.setText("");
                secondhover.setText("Enter number between 1-10");

            }

        });

        comboBox.setOnMouseEntered(mouseEvent -> {
            dropmenuhover.setVisible(true);
        });

        comboBox.setOnMouseExited(mouseEvent -> {
            dropmenuhover.setVisible(false);
        });

        countOfIterationTextField.setOnMouseEntered(mouseEvent -> {
            counthover.setVisible(true);
        });

        countOfIterationTextField.setOnMouseExited(mouseEvent -> {
            counthover.setVisible(false);
        });

        first.setOnMouseEntered(mouseEvent -> {
            firsthover.setVisible(true);
        });

        first.setOnMouseExited(mouseEvent -> {
            firsthover.setVisible(false);
        });

        second.setOnMouseEntered(mouseEvent -> {
            secondhover.setVisible(true);
        });

        second.setOnMouseExited(mouseEvent -> {
            secondhover.setVisible(false);
        });



        startButtonId.setOnAction(actionEvent -> {

            int width = (int) canvasId.getWidth();              // ширина
            int height = (int) canvasId.getHeight();            // висота
            int countOfIteration = 0;                           // кількість ітерацій
            double f = 0;
            double s = 0;

            try {                                               // перевірка чи введене число
                countOfIteration = Integer.parseInt(countOfIterationTextField.getText());
            } catch (NumberFormatException e) {
                HomePageController.showAlert("Error", "Enter count of iteration", Alert.AlertType.ERROR);
                countOfIterationTextField.setText("");
                canvasId.getGraphicsContext2D().clearRect(0, 0, canvasId.getWidth(), canvasId.getHeight());
            }

            if (changeRatio.isSelected()){

            } else  {
                try {
                    f = Integer.parseInt(first.getText());
                } catch (NumberFormatException e) {
                    HomePageController.showAlert("Error", "Enter numerator", Alert.AlertType.ERROR);
                    first.setText("");
                    canvasId.getGraphicsContext2D().clearRect(0, 0, canvasId.getWidth(), canvasId.getHeight());
                }
            }


            try {
                s = Double.parseDouble(second.getText());
            } catch (NumberFormatException e) {
                HomePageController.showAlert("Error", "Enter denominator", Alert.AlertType.ERROR);
                second.setText("");
                canvasId.getGraphicsContext2D().clearRect(0, 0, canvasId.getWidth(), canvasId.getHeight());
            }

            double rez = (f/s * 10);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if ( f == 1 && s == 3){ rez = 3; } if ( f == 1){ rez = s;} if (changeRatio.isSelected()) { rez = s * 10; }
            if(++countOfIteration > 7) {
                countOfIteration--;
            }

            java.awt.Point p1 = new java.awt.Point(width / 2 - height / 3, height / 3);     // початок лінії для фракталу
            java.awt.Point p2 = new java.awt.Point(width / 2 + height / 3, height / 3);     // кінець лінії для фракталу

            try {

                if (comboBox.getValue().equals("Triangle")) {                                     // перевірка на базову фігуру
                    canvasId.getGraphicsContext2D().clearRect(0, 0, canvasId.getWidth(), canvasId.getHeight());

                    java.awt.Point p3 = new java.awt.Point(width / 2, height - height / 9);

                    if (checkBoxId.isSelected()){
                        fractalKoch(canvasId, p2, p1, countOfIteration - 1, rez);           // виклик функції
                        fractalKoch(canvasId, p3, p2, countOfIteration - 1, rez);
                        fractalKoch(canvasId, p1, p3, countOfIteration - 1, rez);
                    } else {
                        fractalKoch(canvasId, p1, p2, countOfIteration - 1, rez);
                        fractalKoch(canvasId, p2, p3, countOfIteration - 1, rez);
                        fractalKoch(canvasId, p3, p1, countOfIteration - 1, rez);
                    }
                } else {
                    canvasId.getGraphicsContext2D().clearRect(0, 0, canvasId.getWidth(), canvasId.getHeight());

                    java.awt.Point pp1 = new java.awt.Point(180, 135);                      // точки для квадрата
                    java.awt.Point pp2 = new java.awt.Point(320, 135);
                    java.awt.Point pp3 = new java.awt.Point(320, 275);
                    java.awt.Point pp4 = new java.awt.Point(180, 275);

                    if (checkBoxId.isSelected()) {
                        fractalKochModified(canvasId, pp2, pp1, countOfIteration - 1, rez); // виклик функції
                        fractalKochModified(canvasId, pp3, pp2, countOfIteration - 1, rez);
                        fractalKochModified(canvasId, pp4, pp3, countOfIteration - 1, rez);
                        fractalKochModified(canvasId, pp1, pp4, countOfIteration - 1, rez);
                    } else {
                        fractalKochModified(canvasId, pp1, pp2, countOfIteration - 1, rez);
                        fractalKochModified(canvasId, pp2, pp3, countOfIteration - 1, rez);
                        fractalKochModified(canvasId, pp3, pp4, countOfIteration - 1, rez);
                        fractalKochModified(canvasId, pp4, pp1, countOfIteration - 1, rez);
                    }

                }

            } catch (NullPointerException e){
                HomePageController.showAlert("Error", "Enter Base Figure", Alert.AlertType.ERROR);
            }


        });



        saveButtonId.setOnAction(actionEvent -> {

            saveToFile(canvasId);

        });

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

        colorSchemeButtonId.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/colorSchemePage.fxml"));
                Stage stage = (Stage) colorSchemeButtonId.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Color Scheme");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        parallelogramButtonId.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/parallelpgramPage.fxml"));
                Stage stage = (Stage) parallelogramButtonId.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Parallelogram");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        helpButtonId.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/helpPage.fxml"));
//                Stage stage = (Stage) helpButtonId.getScene().getWindow();
//                stage.setScene(new Scene(root));
//                stage.setTitle("Help");
                Stage stage = new Stage();
                stage.setTitle("Fractal Help");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        learningButton.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/fractalLearningPage.fxml"));
                Stage stage = (Stage) learningButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Learning Fractal");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void saveToFile(Canvas canvas) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Файли зображень (*.jpg, *.png)", "*.jpg", "*.png"));
        try {
            File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());
            if (file != null) {

                WritableImage writableImage = new WritableImage((int) (canvas.getWidth()), (int) canvas.getHeight());

                canvas.snapshot(null, writableImage);
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null),
                        "png", file);
            }
        } catch (IOException e) {
            HomePageController.showAlert("Помилка", "Помилка запису файлу", Alert.AlertType.ERROR);
        }
    }

    private void fractalKoch (Canvas canvas, java.awt.Point p1, java.awt.Point p2, int count, double s){        // метод для трикутника

        if( count < 1 ){
            canvas.getGraphicsContext2D().strokeLine(p1.x, p1.y, p2.x, p2.y);
            return;
        }

        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        float distance  = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        float kut = (float) Math.atan2(dy, dx);

        java.awt.Point m1 = new java.awt.Point((int) (p1.x + dx / s), (int) (p1.y + dy / s));
        java.awt.Point m2 = new java.awt.Point((int) (p2.x - dx / s), (int) (p2.y - dy / s));
        java.awt.Point m3;
        if ( s <= 4 ){
            m3 = new java.awt.Point((int) (m1.x + Math.cos(kut - Math.PI / s) * (distance / 3)), (int) (m1.y + Math.sin(kut - Math.PI / s) * (distance / 3))); // визначення точки
        } else {

            int x = (int) (s - (s - 1)); // s + 3
            int y = (int) (s - (s - 1));

            m3 = new java.awt.Point((int) (m1.x + Math.cos(kut - Math.PI / (s - x)) * (distance / 2.35)), (int) (m1.y + Math.sin(kut - Math.PI / (s - y)) * (distance / 2.35))); // визначення точки
        }

        fractalKoch(canvas, p1, m1, count - 1, s);      // рекурсія
        fractalKoch(canvas, m2, p2, count - 1, s);
        fractalKoch(canvas, m1, m3, count - 1, s);
        fractalKoch(canvas, m3, m2, count - 1, s);
    }

    private void fractalKochModified (Canvas canvas, java.awt.Point p1, java.awt.Point p2, int count, double s){    // метод для квадрата

        if( count < 1 ){
            canvas.getGraphicsContext2D().strokeLine(p1.x, p1.y, p2.x, p2.y);
            return;
        }

        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        float distance  = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        float kut = (float) Math.atan2(dy, dx);

        java.awt.Point m1 = new java.awt.Point((int) (p1.x + dx / s), (int) (p1.y + dy / s)); // визначення точки
        java.awt.Point m2 = new java.awt.Point((int) (p2.x - dx / s), (int) (p2.y - dy / s)); // визначення точки
        java.awt.Point m3 = new java.awt.Point((int) (m1.x + Math.cos(kut - Math.PI / 2) * (distance / 3)), (int) (m1.y + Math.sin(kut - Math.PI / 2) * (distance / 3))); // визначення точки
        java.awt.Point m4 = new java.awt.Point((int) (m2.x + Math.cos(kut - Math.PI / 2) * (distance / 3)), (int) (m2.y + Math.sin(kut - Math.PI / 2) * (distance / 3))); // визначення точки

        fractalKochModified(canvas, p1, m1, count - 1, s);      // рекурсія
        fractalKochModified(canvas, m2, p2, count - 1, s);
        fractalKochModified(canvas, m1, m3, count - 1, s);
        fractalKochModified(canvas, m3, m4, count - 1, s);
        fractalKochModified(canvas, m4, m2, count - 1, s);
    }
}
