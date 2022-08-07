package sample.controllers;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import static javafx.scene.text.TextAlignment.CENTER;

public class ParallelogramController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPaneid;

    @FXML
    private Button homeButtonId;

    @FXML
    private Button fractalButtonId;

    @FXML
    private Button colorShcemeButtonId;

    @FXML
    private Button helpButtonId;

    @FXML
    private Button createParallelogramButtonId;

    @FXML
    private Button rotateAndMinimizeButtonId;

    @FXML
    private TextField AX;

    @FXML
    private TextField BX;

    @FXML
    private TextField CX;

    @FXML
    private TextField DX;

    @FXML
    private TextField DY;

    @FXML
    private TextField CY;

    @FXML
    private TextField BY;

    @FXML
    private TextField AY;

    @FXML
    private TextField minimizeInTextFieldid;

    @FXML
    private TextField measureTextFieldid;

    @FXML
    private TextField angleTextFieldid;

    @FXML
    private RadioButton radioButtonIdA;

    @FXML
    private RadioButton radioButtonIdB;

    @FXML
    private RadioButton radioButtonIdC;

    @FXML
    private RadioButton radioButtonIdD;

    @FXML
    private Canvas canvas;

    @FXML
    private Button saveButton;

    @FXML
    private Button learningButton;

    @FXML
    private Label coordinateDLabel;

    @FXML
    private Label labelA;

    @FXML
    private Label labelA1;

    @FXML
    private Label labelA2;

    @FXML
    private int measure = 30;

    private Parallelogram parallelogram;

    @FXML
    void initialize() {

        AX.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    AX.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        AY.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    AY.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        BX.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    BX.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        BY.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    BY.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        CX.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    CX.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        CY.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    CY.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        DX.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    DX.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        DY.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    DY.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        measureTextFieldid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    measureTextFieldid.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        minimizeInTextFieldid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    minimizeInTextFieldid.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        angleTextFieldid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    angleTextFieldid.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().setTextAlign(CENTER);

        ToggleGroup group = new ToggleGroup();
        radioButtonIdA.setToggleGroup(group);
        radioButtonIdB.setToggleGroup(group);
        radioButtonIdC.setToggleGroup(group);
        radioButtonIdD.setToggleGroup(group);
        radioButtonIdA.setSelected(true);

        radioButtonIdA.setDisable(true);
        radioButtonIdB.setDisable(true);
        radioButtonIdC.setDisable(true);
        radioButtonIdD.setDisable(true);

        angleTextFieldid.setDisable(true);
        minimizeInTextFieldid.setDisable(true);
        rotateAndMinimizeButtonId.setDisable(true);


        measureTextFieldid.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                measure = Integer.parseInt(measureTextFieldid.getText());
                if ( measure >= 20 && measure <= 50) {
                    drawCoordinate(canvas, measure);
                } else {
                    HomePageController.showAlert("Error", "Your measure is too long or too small.\nPlease Enter measure between 20 and 50", Alert.AlertType.ERROR);
                }
            }
        });


        AX.setOnMouseEntered(mouseEvent -> {
            labelA.setVisible(true);
        });

        AX.setOnMouseExited(mouseEvent -> {
            labelA.setVisible(false);
        });

        AY.setOnMouseEntered(mouseEvent -> {
            labelA.setVisible(true);
        });
        AY.setOnMouseExited(mouseEvent -> {
            labelA.setVisible(false);
        });

        BX.setOnMouseEntered(mouseEvent -> {
            labelA1.setVisible(true);
        });

        BX.setOnMouseExited(mouseEvent -> {
            labelA1.setVisible(false);
        });

        BY.setOnMouseEntered(mouseEvent -> {
            labelA1.setVisible(true);
        });

        BY.setOnMouseExited(mouseEvent -> {
            labelA1.setVisible(false);
        });

        CX.setOnMouseEntered(mouseEvent -> {
            labelA2.setVisible(true);
        });
        CX.setOnMouseExited(mouseEvent -> {
            labelA2.setVisible(false);
        });

        CY.setOnMouseEntered(mouseEvent -> {
            labelA2.setVisible(true);
        });

        CY.setOnMouseExited(mouseEvent -> {
            labelA2.setVisible(false);
        });

        DX.setOnMouseEntered(mouseEvent -> {
            coordinateDLabel.setVisible(true);
        });

        DX.setOnMouseExited(mouseEvent -> {
            coordinateDLabel.setVisible(false);
        });

        DY.setOnMouseEntered(mouseEvent -> {
            coordinateDLabel.setVisible(true);
        });

        DY.setOnMouseExited(mouseEvent -> {
            coordinateDLabel.setVisible(false);
        });

        createParallelogramButtonId.setOnAction(actionEvent -> {                    // метод опрацюванна дії на кнопку

            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            measure = Integer.parseInt(measureTextFieldid.getText());
            if ( measure >= 20 || measure <= 50){
                drawCoordinate(canvas, measure);
                try {
                    double ax = Double.parseDouble(AX.getText());                       // координата X для точки А
                    double ay = Double.parseDouble(AY.getText());                       // координата Y для точки А
                    double bx = Double.parseDouble(BX.getText());                       // координата X для точки B
                    double by = Double.parseDouble(BY.getText());                       // координата Y для точки B
                    double cx = Double.parseDouble(CX.getText());                       // координата X для точки C
                    double cy = Double.parseDouble(CY.getText());                       // координата Y для точки C
                    double dx = 0;                                                      // координата X для точки D
                    double dy = 0;                                                      // координата Y для точки D

                    if (DX.getText().equals("") && DY.getText().equals("")){            // перевірка чи координати для точки D пусті
                        dx = cx - ax;                                                   // обрахунок координати X для точки D
                        dy = by - cy + 1;                                                   // обрахунок координати Y для точки D
                    } else {
                        dx = Double.parseDouble(DX.getText());
                        dy = Double.parseDouble(DY.getText());
                    }

                    double mid1x = (ax + cx) / 2;                                       // знаходження центру діагоналей
                    double mid1y = (ay + cy) / 2;                                       // знаходження центру діагоналей
                    double mid2x = (bx + dx) / 2;                                       // знаходження центру діагоналей
                    double mid2y = (by + dy) / 2;                                       // знаходження центру діагоналей

                    System.out.println(mid1x + " " + mid1y);
                    System.out.println(mid2x + " " + mid2y);

                    if (mid1x == mid2x && mid1y == mid2y){                              // перевірка правильності паралелограма
                        parallelogram = new Parallelogram(new Point(ax, ay), new Point(bx, by), new Point(cx, cy), new Point(dx,dy));
                    } else {
                        HomePageController.showAlert("Error", "That is not the parallelogram.\nEnter correct data", Alert.AlertType.ERROR);
                    }

                } catch (NumberFormatException e) {
                    HomePageController.showAlert("Error", "Enter correct data", Alert.AlertType.ERROR);
                } catch (NullPointerException ne) {
                    HomePageController.showAlert("Error", "Enter correct data", Alert.AlertType.ERROR);
                }
                drawParallelogram(canvas, parallelogram, measure);
            } else {
                HomePageController.showAlert("Error", "Your measure is too long or too small.\nPlease Enter measure between 20 and 50", Alert.AlertType.ERROR);
            }



            radioButtonIdA.setDisable(false);
            radioButtonIdB.setDisable(false);
            radioButtonIdC.setDisable(false);
            radioButtonIdD.setDisable(false);

            angleTextFieldid.setDisable(false);
            minimizeInTextFieldid.setDisable(false);
            rotateAndMinimizeButtonId.setDisable(false);

        });

        rotateAndMinimizeButtonId.setOnAction(actionEvent -> {

            // додати перевірку на пусті поля зменшення та повороту

            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            drawCoordinate(canvas, measure);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    double angle1;                                          // тимчасова змінна для обрахунку кута
                    double angle;                                           // змінна для кута
                    double scale;                                           // змінна для зменшення фігури

                    Point rotatePoint = new Point();                        // об'єкт класу Point

                    if (radioButtonIdA.isSelected()) {
                        rotatePoint.setX(parallelogram.getA().getX());
                        rotatePoint.setY(parallelogram.getA().getY());
                    } else if (radioButtonIdB.isSelected()) {
                        rotatePoint.setX(parallelogram.getB().getX());
                        rotatePoint.setY(parallelogram.getB().getY());
                    } else if (radioButtonIdB.isSelected()) {
                        rotatePoint.setX(parallelogram.getC().getX());
                        rotatePoint.setY(parallelogram.getC().getY());
                    } else {
                        rotatePoint.setX(parallelogram.getD().getX());
                        rotatePoint.setY(parallelogram.getD().getY());
                    }

                    try{
                        angle1 = Double.parseDouble(angleTextFieldid.getText());        // отримання числа для кута
                        angle = getAngle(angle1);                                       // переведення з градусів у радіани
                        scale = Double.parseDouble(minimizeInTextFieldid.getText());    // отримання числа у скільки разів зменшити фігуру


                            double an = angle / 100;
                            double s = 1;

                            for (int i = 0; i < 100; i++) {                                 // цикл для плавного повороту та зменшення

                                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                                measure = Integer.parseInt(measureTextFieldid.getText());
                                drawCoordinate(canvas, measure);

                                transform(parallelogram.getA(), rotatePoint, an, s);        // перетворення для точки А
                                transform(parallelogram.getB(), rotatePoint, an, s);        // перетворення для точки B
                                transform(parallelogram.getC(), rotatePoint, an, s);        // перетворення для точки C
                                transform(parallelogram.getD(), rotatePoint, an, s);        // перетворення для точки D

                                s += scale / 20000;

                                drawParallelogram(canvas, parallelogram, measure);

                                try {
                                    Thread.sleep(15);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                    } catch (NumberFormatException e) {
                        HomePageController.showAlert("Error", "Enter correct data", Alert.AlertType.ERROR);
                    } catch (NullPointerException e) {
                        HomePageController.showAlert("Error", "Enter correct data", Alert.AlertType.ERROR);
                    }
                }
            });
            thread.start();

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

        fractalButtonId.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/fractalPage.fxml"));
                Stage stage = (Stage) fractalButtonId.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Fractal");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        colorShcemeButtonId.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/colorSchemePage.fxml"));
                Stage stage = (Stage) colorShcemeButtonId.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Color Scheme");

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        helpButtonId.setOnAction(actionEvent -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/affineHelp.fxml"));
//                Stage stage = (Stage) helpButtonId.getScene().getWindow();
//                stage.setScene(new Scene(root));
//                stage.setTitle("Help");
                Stage stage = new Stage();
                stage.setTitle("Affine Transformation Help");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        saveButton.setOnAction(actionEvent -> {
            saveToFile(canvas);
        });

        learningButton.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/parallelpgramLearningPage.fxml"));
                Stage stage = (Stage) learningButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Learning affine transformation");

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

    private static void drawCoordinate(Canvas canvas, int measure){

        int k = measure;
        int j = 0;

        canvas.getGraphicsContext2D().strokeLine(canvas.getWidth()/2, 20, canvas.getWidth()/2, canvas.getHeight()-20);
        canvas.getGraphicsContext2D().strokeLine(20, canvas.getHeight()/2, canvas.getWidth()-20, canvas.getHeight()/2);

        canvas.getGraphicsContext2D().strokeText("Y", canvas.getWidth()/2 - 15, 25);
        canvas.getGraphicsContext2D().strokeLine(canvas.getWidth()/2 - 7, 30, canvas.getWidth()/2, 20);
        canvas.getGraphicsContext2D().strokeLine(canvas.getWidth()/2 + 7, 30, canvas.getWidth()/2, 20);

        canvas.getGraphicsContext2D().strokeText("X", canvas.getWidth() - 22, canvas.getHeight()/2 + 20);
        canvas.getGraphicsContext2D().strokeLine(canvas.getWidth() - 27, canvas.getHeight()/2 - 7, canvas.getWidth() - 20, canvas.getHeight()/2);
        canvas.getGraphicsContext2D().strokeLine(canvas.getWidth() - 27, canvas.getHeight()/2 + 7, canvas.getWidth() - 20, canvas.getHeight()/2);

        for (int i = 0; i < 10; i++){

            if ( k < 230) {
                canvas.getGraphicsContext2D().strokeLine(canvas.getWidth()/2 - 5, canvas.getHeight()/2 - k, canvas.getWidth()/2 + 5, canvas.getHeight()/2 - k);
                canvas.getGraphicsContext2D().strokeLine(canvas.getWidth()/2 - 5, canvas.getHeight()/2 + k, canvas.getWidth()/2 + 5, canvas.getHeight()/2 + k);

                canvas.getGraphicsContext2D().strokeLine(canvas.getWidth()/2 - k, canvas.getHeight()/2 - 5, canvas.getWidth()/2 - k, canvas.getHeight()/2 + 5);
                canvas.getGraphicsContext2D().strokeLine(canvas.getWidth()/2 + k, canvas.getHeight()/2 - 5, canvas.getWidth()/2 + k, canvas.getHeight()/2 + 5);

                canvas.getGraphicsContext2D().strokeText(String.valueOf(i + 1), canvas.getWidth()/2 - 15, canvas.getHeight()/2 - k + 5);
                canvas.getGraphicsContext2D().strokeText(String.valueOf(j - 1), canvas.getWidth()/2 - 15, canvas.getHeight()/2 + k + 5);

                canvas.getGraphicsContext2D().strokeText(String.valueOf(j - 1), canvas.getWidth()/2 - k, canvas.getHeight()/2 + 20);
                canvas.getGraphicsContext2D().strokeText(String.valueOf(i + 1), canvas.getWidth()/2 + k, canvas.getHeight()/2 + 20);
                k += measure;
                j--;
            }
            else break;
        }
    }

    private void drawParallelogram(Canvas canvas, Parallelogram parallelogram, int measure){

        int hWidth = (int) (canvas.getWidth()/2);
        int hHeight = (int) (canvas.getHeight()/2);

//        canvas.getGraphicsContext2D().fillPolygon(
//                new double[]{
//                    parallelogram.getA().getX() * measure + hWidth,
//                    parallelogram.getB().getX() * measure + hWidth,
//                    parallelogram.getC().getX() * measure + hWidth,
//                    parallelogram.getD().getX() * measure + hWidth
//                },
//                new double[]{
//                    hHeight - parallelogram.getA().getY() * measure,
//                    hHeight - parallelogram.getB().getY() * measure,
//                    hHeight - parallelogram.getC().getY() * measure,
//                    hHeight - parallelogram.getD().getY() * measure
//                }, 3
//        );
        canvas.getGraphicsContext2D().strokePolygon(
                new double[]{
                        parallelogram.getA().getX() * measure + hWidth,
                        parallelogram.getB().getX() * measure + hWidth,
                        parallelogram.getC().getX() * measure + hWidth,
                        parallelogram.getD().getX() * measure + hWidth
                },
                new double[]{
                        hHeight - parallelogram.getA().getY() * measure,
                        hHeight - parallelogram.getB().getY() * measure,
                        hHeight - parallelogram.getC().getY() * measure,
                        hHeight - parallelogram.getD().getY() * measure
                }, 4
        );

    }

    private static double getAngle(double angle){
        return (Math.PI / 180) * angle;
    }

    private static double[][] multiply(double[][] a, double[][] b) {        // метод для множення матриць

        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;

        double[][] result = new double[m1][n2];

        for (int i = 0; i < m1; i++){
            for (int j = 0; j < n2; j++){
                for (int k = 0; k < a.length; k++){
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    public static double[] multiply(double[] x, double[][] a) {             // метод для множення вектора та матриці
        int m = a.length;
        int n = a[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += a[i][j] * x[i];
        return y;
    }

    private static void transform(Point point, Point rotatePoint, double angle, double scale) {

        double[] coordinates = new double[] { point.getX(), point.getY(), 1 };      // вектор

        double[][] t1 = new double[][]{                                             // матриця зміщення відносно точки
                {1, 0, 0},
                {0, 1, 0},
                {-rotatePoint.getX(), -rotatePoint.getY(), 1}};

        double[][] t2 = new double[][]{                                             // матриця зміщення відносно точки
                {1, 0, 0},
                {0, 1, 0},
                {rotatePoint.getX(), rotatePoint.getY(), 1}};

        double[][] r = new double[][]{                                              // матриця повороту відносно точки
                {Math.cos(angle),-Math.sin(angle),0},
                {Math.sin(angle),Math.cos(angle),0},
                {0,0,1}};

        double[][] scaleMatr = new double[][]{                                      // матриця зменшення
                {1/scale, 0, 0},
                {0, 1/scale, 0},
                {0, 0, 1}};

        double[] result = multiply(coordinates, multiply(multiply(multiply(t1, r), scaleMatr), t2));

       point.setX(result[0]);
       point.setY(result[1]);

    }

}