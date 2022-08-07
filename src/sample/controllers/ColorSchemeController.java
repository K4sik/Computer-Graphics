package sample.controllers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class ColorSchemeController {

    @FXML
    public TextField saturation_text_id;

    @FXML
    private Button homeButtonId;

    @FXML
    private Button fractalButtonId;

    @FXML
    private Button parallelogramButtonId;

    @FXML
    private Button helpButtonId;

    @FXML
    private Button loadIngButtonId;

    @FXML
    private Button saveImgButtonId;

    @FXML
    private Button transformToHSVButtonId;

    @FXML
    private Slider sliderId;

    @FXML
    private Text RGBTextid;

    @FXML
    private Text HSVTextId;

    @FXML
    private Canvas RGBCanvasId;

    @FXML
    private Canvas HSVCanvasId;

    @FXML
    private Button learningButton;

    private BufferedImage bufferedImage;


    @FXML
    void initialize() {

        sliderId.setMin(0);
        sliderId.setMax(1);
        sliderId.setValue(0.5);

        saveImgButtonId.setDisable(true);
        sliderId.setDisable(true);

        RGBCanvasId.setDisable(true);
        HSVCanvasId.setDisable(true);
        saturation_text_id.setEditable(false);

        loadIngButtonId.setOnAction(actionEvent -> {
            openFile(RGBCanvasId);
            HSVCanvasId.getGraphicsContext2D().clearRect(0,0,HSVCanvasId.getWidth(), HSVCanvasId.getHeight());

        });

        saveImgButtonId.setOnAction(actionEvent -> {
            saveToFile(HSVCanvasId);
        });

        learningButton.setOnAction(actionEvent -> {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/colorLearningPage.fxml"));
                Stage stage = (Stage) learningButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Learning Color Scheme");

//                Stage stage = new Stage();
//                stage.setTitle("Learning Color Scheme");
//                stage.setScene(new Scene(root));
//                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        transformToHSVButtonId.setOnAction(actionEvent -> {                 // опрацювання дії на кнопку

            try {
                WritableImage wImage = new WritableImage( bufferedImage.getWidth(), bufferedImage.getHeight());
                PixelWriter pixelWriter = wImage.getPixelWriter();

                for (int i = 0; i < bufferedImage.getWidth(); i++){         // цикл по ширині
                    for (int j = 0; j < bufferedImage.getHeight(); j++){    // цикл по висоті
                        int pixel = bufferedImage.getRGB(i, j);             // отримання значення rgb кожного пікселя
                        int red = (pixel >> 16) & 0xFF;                     // запис у змінну значення червоного кольору
                        int green = (pixel >> 8) & 0xFF;                    // запис у змінну значення зеленого кольору
                        int blue = (pixel >> 0) & 0xFF;                     // запис у змінну значення синього кольору

                        pixelWriter.setColor(i, j, transformImg(red, green, blue)); // перетворення RHB в HSV

                    }
                }
                HSVCanvasId.getGraphicsContext2D().drawImage(wImage,0,0);   // виведення фото на екран

                RGBCanvasId.setDisable(false);
                HSVCanvasId.setDisable(false);

                saveImgButtonId.setDisable(false);
                sliderId.setDisable(false);
                saturation_text_id.setEditable(true);

            } catch (NullPointerException e) {
                HomePageController.showAlert("Error", "Chose your image", Alert.AlertType.ERROR);
            }

        });

        RGBCanvasId.setOnMouseClicked(mouseEvent -> {
            mouseEvent.getX();
            mouseEvent.getY();

            int pixel = bufferedImage.getRGB((int) mouseEvent.getX(), (int) mouseEvent.getY());     // отримання значення rgb вибраного пікселя

            int red = (pixel >> 16) & 0xFF;                // запис у змінну значення червоного кольору
            int green = (pixel >> 8) & 0xFF;               // запис у змінну значення зеленого кольору
            int blue = (pixel >> 0) & 0xFF;                // запис у змінну значення синього кольору

            RGBTextid.setText("RGB( " + red + ", " + green + ", " + blue + " )"); // виведення значень rgb

            float[] hsv = RGBtoHSV(red, green, blue);       // запис значень у масив

            HSVTextId.setText("HSV( " + String.format("%.0f", hsv[0]) + ", " + String.format("%.0f", hsv[1] * 100) + "%, " + String.format("%.0f", hsv[2] * 100) + "% )");// виведення значень hsv

        });

        HSVCanvasId.setOnMouseClicked(mouseEvent -> {
            mouseEvent.getX();
            mouseEvent.getY();

            int pixel = bufferedImage.getRGB((int) mouseEvent.getX(), (int) mouseEvent.getY());     // отримання значення rgb вибраного пікселя

            int red = (pixel >> 16) & 0xFF;                 // запис у змінну значення червоного кольору
            int green = (pixel >> 8) & 0xFF;                // запис у змінну значення зеленого кольору
            int blue = (pixel >> 0) & 0xFF;                 // запис у змінну значення синього кольору

            RGBTextid.setText("RGB( " + red + ", " + green + ", " + blue + " )");   // виведення значень rgb

            float[] hsv = RGBtoHSV(red, green, blue);       // запис значень у масив

            HSVTextId.setText("HSV( " + String.format("%.0f", hsv[0]) + ", " + String.format("%.0f", hsv[1] * 100) + "%, " + String.format("%.0f", hsv[2] * 100) + "% )");// виведення значень hsv

        });

        sliderId.setOnMouseDragged(mouseEvent -> {

            WritableImage wImage = new WritableImage( bufferedImage.getWidth(), bufferedImage.getHeight());
            PixelWriter pixelWriter = wImage.getPixelWriter();

            for (int i = 0; i < bufferedImage.getWidth(); i++){         // цикл по ширині
                for (int j = 0; j < bufferedImage.getHeight(); j++){    // цикл по висоті

                    int pixel = bufferedImage.getRGB(i, j);             // отримання значення rgb кожного пікселя
                    int red = (pixel >> 16) & 0xFF;                     // запис у змінну значення червоного кольору
                    int green = (pixel >> 8) & 0xFF;                    // запис у змінну значення зеленого кольору
                    int blue = (pixel >> 0) & 0xFF;                     // запис у змінну значення синього кольору

                    float[] hsv = RGBtoHSV(red, green, blue);           // запис значень у масив

                    if (hsv[0] >= 80 && hsv[0] <= 160){                 // знаходження відповідного кольору
                        hsv[1] = (float) sliderId.getValue();           // зміна насиченості
                        saturation_text_id.setText(String.format("%.2f", sliderId.getValue() ));
                    }

                    int[]rgb = HSVtoRGB(hsv[0], hsv[1], hsv[2]);        // перетворення з hsb у rgb

                    pixelWriter.setColor(i, j, javafx.scene.paint.Color.rgb(rgb[0],rgb[1],rgb[2])); // вивід на екран
                }
            }
            HSVCanvasId.getGraphicsContext2D().drawImage(wImage,0,0);

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

                Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/colorHelp.fxml"));
//                Stage stage = (Stage) helpButtonId.getScene().getWindow();
//                stage.setScene(new Scene(root));
//                stage.setTitle("Help");
                Stage stage = new Stage();
                stage.setTitle("Color Help");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    public javafx.scene.paint.Color transformImg(int red, int green, int blue){

        float r = red / 255f;
        float g = green / 255f;
        float b = blue / 255f;

        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));
        float delta = max - min;

        float hue = 0;
        float saturation = 0;
        float value = 0;

        //Hue
        if (max == min){
            hue = 0;
        } else if (max == r){
            hue = ((g - b) / delta) * 60f;
        } else if (max == g){
            hue = ((b - r) / delta + 2f) * 60f;
        } else if (max == b){
            hue = ((r - g) / delta + 4f) * 60f;
        }

        // Saturation
        if (delta == 0)
            saturation = 0;
        else
            saturation = delta / max;

        //Value
        value = max;



        //HSVtoRGB

        float hi = (float)Math.floor(hue / 60.0) % 6;
        float f =  (float)((hue / 60.0) - Math.floor(hue / 60.0));
        float p = (float)(value * (1.0 - saturation));
        float q = (float)(value * (1.0 - (f * saturation)));
        float t = (float)(value * (1.0 - ((1.0 - f) * saturation)));

        int rr = red;
        int gg = green;
        int bb = blue;

        if (hi == 0){
            rr = (int)(value * 255);
            gg = (int)(t * 255);
            bb = (int)(p * 255);
        }
        else if (hi == 1){
            rr = (int)(q * 255);
            gg = (int)(value * 255);
            bb = (int)(p * 255);
        }
        else if (hi == 2){
            rr = (int)(p * 255);
            gg = (int)(value * 255);
            bb = (int)(t * 255);
        }
        else if (hi == 3){
            rr = (int)(p * 255);
            gg = (int)(q * 255);// p q v
            bb = (int)(value * 255);
        }
        else if (hi == 4){
            rr = (int)(t * 255);
            gg = (int)(p * 255);// t p v
            bb = (int)(value * 255);
        }
        else if (hi == 5){
            rr = (int)(value * 255);
            gg = (int)(p * 255);
            bb = (int)(q * 255);
        }

        return javafx.scene.paint.Color.rgb(rr, gg, bb);
    }

    public float[] RGBtoHSV(int red, int green, int blue){          // метод для перетворення GRB у HSV

        float[] hsv = new float[3];
        float r = red / 255f;
        float g = green / 255f;
        float b = blue / 255f;

        float max = Math.max(r, Math.max(g, b));        // знаходження максимального числа
        float min = Math.min(r, Math.min(g, b));        // знаходження мінімального числа
        float delta = max - min;                        // знаходження різниці між max та min

        // Hue
        if (max == min){
            hsv[0] = 0;
        } else if (max == r){
            hsv[0] = ((g - b) / delta) * 60f;
        } else if (max == g){
            hsv[0] = ((b - r) / delta + 2f) * 60f;
        } else if (max == b){
            hsv[0] = ((r - g) / delta + 4f) * 60f;
        }

        // Saturation
        if (delta == 0)
            hsv[1] = 0;
        else
            hsv[1] = delta / max;

        //Value
        hsv[2] = max;
        return hsv;
    }

    public static int[] HSVtoRGB(float hue, float saturation, float value){     // метод для перетворення HSV у RGB
        int[] rgb = new int[3];

        float hi = (float)Math.floor(hue / 60.0) % 6;
        float f = (float)((hue / 60.0) - Math.floor(hue / 60.0));
        float p = (float)(value * (1.0 - saturation));
        float q = (float)(value * (1.0 - (f * saturation)));
        float t = (float)(value * (1.0 - ((1.0 - f) * saturation)));

        if (hi == 0){
            rgb[0] = (int)(value * 255);
            rgb[1] = (int)(t * 255);
            rgb[2] = (int)(p * 255);
        }
        else if (hi == 1){
            rgb[0] = (int)(q * 255);
            rgb[1] = (int)(value * 255);
            rgb[2] = (int)(p * 255);
        }
        else if (hi == 2){
            rgb[0] = (int)(p * 255);
            rgb[1] = (int)(value * 255);
            rgb[2] = (int)(t * 255);
        }
        else if (hi == 3){
            rgb[0] = (int)(p * 255);
            rgb[1] = (int)(q * 255);// p q v
            rgb[2] = (int)(value * 255);
        }
        else if (hi == 4){
            rgb[0] = (int)(t * 255);
            rgb[1] = (int)(p * 255);// t p v
            rgb[2] = (int)(value * 255);
        }
        else if (hi == 5){
            rgb[0] = (int)(value * 255);
            rgb[1] = (int)(p * 255);
            rgb[2] = (int)(q * 255);
        }

        return rgb;
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

    private void openFile(Canvas canvas) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            Image image = ImageIO.read(file);

            Image toolkitImage = image.getScaledInstance((int) canvas.getWidth(), (int) canvas.getHeight(), Image.SCALE_SMOOTH);

            int width = toolkitImage.getWidth(null);
            int height = toolkitImage.getHeight(null);

            bufferedImage = new BufferedImage(width, height, Image.SCALE_SMOOTH);

            bufferedImage.getGraphics().drawImage(toolkitImage, 0, 0, null);

            canvas.getGraphicsContext2D().drawImage(SwingFXUtils.toFXImage(bufferedImage, null), 0, 0);

        } catch (IOException ex) {
            HomePageController.showAlert("Помилка", "Помилка відкриття файлу", Alert.AlertType.ERROR);        }
    }

}