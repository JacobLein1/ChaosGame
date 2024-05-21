package view;

import controller.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Vector2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fractal extends Application {
    private Stage stage;
    private TextField steps;
    private String pageTitle;
    private final BorderPane root = new BorderPane();
    private ChaosGame chaosGame;
    private ChaosGameDescription chaosGameDescription;
    private int width;
    private int height;
    private Button showFractal;
    private TextField minCoordX0;
    private TextField minCoordX1;
    private TextField maxCoordX0;
    private TextField maxCoordX1;
    private ImageView imageView;
    private Label imageFileText;
    private Label errorLabel = new Label();
    private ToggleButton toggleButton = new ToggleButton("Off");

    public void setMenu(){
        Label numberLabel = new Label("Number of steps:");
        steps = new TextField();
        showFractal = new Button("Show");
        Button home = new Button("Home");
        Label minCoordLabel = new Label("Minimum coordinates:");
        minCoordX0 = new TextField();
        minCoordX1 = new TextField();
        Label maxCoordLabel = new Label("Maximum coordinates:");
        maxCoordX0 = new TextField();
        maxCoordX1 = new TextField();
        Button saveFractalAsImage = new Button("Save image");
        imageFileText = new Label();

        minCoordX0.setPromptText("X0");
        minCoordX1.setPromptText("X1");
        maxCoordX0.setPromptText("X0");
        maxCoordX1.setPromptText("X1");

        numberLabel.getStyleClass().add("menu-label");
        steps.getStyleClass().add("steps-field");
        showFractal.getStyleClass().add("big-menu-button");
        home.getStyleClass().add("big-menu-button");
        minCoordLabel.getStyleClass().add("menu-label");
        minCoordX0.getStyleClass().add("menu-field");
        minCoordX1.getStyleClass().add("menu-field");
        maxCoordLabel.getStyleClass().add("menu-label");
        maxCoordX0.getStyleClass().add("menu-field");
        maxCoordX1.getStyleClass().add("menu-field");
        saveFractalAsImage.getStyleClass().add("big-menu-button");

        home.setOnAction(actionEvent -> {
            HomePage homePage = new HomePage();
            homePage.start(stage);
        });
        errorLabel.setText("");
        errorLabel.setStyle("-fx-text-fill: red;");

        saveFractalAsImage.setOnAction(actionEvent -> {
            try {
                saveAsImage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        VBox minVector = new VBox(minCoordX0, minCoordX1);
        VBox maxVector = new VBox(maxCoordX0, maxCoordX1);
        VBox.setMargin(minCoordX1, new Insets(5,0,10,0));
        VBox.setMargin(maxCoordX0, new Insets(0,0,5,0));
        VBox.setMargin(maxCoordX1, new Insets(0,0,5,0));
        HBox vectorBox = new HBox(minCoordLabel, minVector, maxCoordLabel, maxVector);
        HBox stepsBox = new HBox(numberLabel, steps);
        HBox.setMargin(steps, new Insets(10,0,20,34));


        toggleButton.setOnAction(actionEvent -> {
            if (toggleButton.isSelected()){
                toggleButton.setText("On");
            } else {
                toggleButton.setText("Off");
            }
        });
        Label colorMode = new Label("ColorMode");
        VBox colorModeBox = new VBox(colorMode, toggleButton);
        VBox inputBoxes = new VBox(stepsBox, vectorBox);
        HBox errorBox = new HBox(errorLabel);
        HBox buttonBox = new HBox(showFractal, saveFractalAsImage, home);
        VBox buttonAndTextBox = new VBox(colorModeBox, errorBox, buttonBox, imageFileText);
        buttonAndTextBox.setAlignment(Pos.BOTTOM_RIGHT);
        imageFileText.setAlignment(Pos.BOTTOM_RIGHT);

        HBox menu = new HBox(inputBoxes, buttonAndTextBox);
        HBox.setMargin(buttonAndTextBox, new Insets(0,0,0,100));
        HBox.setMargin(numberLabel, new Insets(20,20,10,10));
        HBox.setMargin(stepsBox, new Insets(20,10,20,10));
        HBox.setMargin(minCoordLabel, new Insets(20,20,10,10));
        HBox.setMargin(maxCoordLabel, new Insets(20,20,10,10));
        HBox.setMargin(showFractal, new Insets(20,10,10,20));
        HBox.setMargin(home, new Insets(20,10,10,20));
        HBox.setMargin(saveFractalAsImage, new Insets(20,10,10,20));
        VBox.setMargin(imageFileText, new Insets(0,20,0,0));
        menu.getStyleClass().add("menu-background");

        root.setTop(menu);
        root.getStyleClass().add("background");
    }

    public void showFractalOnAction(){
        showFractal.setOnAction(actionEvent -> {
            errorLabel.setText("");
            if (minCoordX0.getText().isEmpty() || minCoordX1.getText().isEmpty()
                    || maxCoordX0.getText().isEmpty() || maxCoordX1.getText().isEmpty() || steps.getText().isEmpty()){
                errorLabel.setText("Please fill in all fields");
                return;
            }

            try {
                double minCoordX0Value = Double.parseDouble(minCoordX0.getText());
                double minCoordX1Value = Double.parseDouble(minCoordX1.getText());
                double maxCoordX0Value = Double.parseDouble(maxCoordX0.getText());
                double maxCoordX1Value = Double.parseDouble(maxCoordX1.getText());
                if (minCoordX0Value >= maxCoordX0Value || minCoordX1Value >= maxCoordX1Value){
                    errorLabel.setText("Minimum coordinates must be less than maximum coordinates");
                    return;
                }
                chaosGameDescription.setMinCoords(new Vector2D(minCoordX0Value, minCoordX1Value));
                chaosGameDescription.setMaxCoords(new Vector2D(maxCoordX0Value, maxCoordX1Value));

                chaosGame = new ChaosGame(chaosGameDescription, width, height);
                displayFractal();
            } catch (NumberFormatException e) {
                errorLabel.setText("Please enter valid numbers for coordinates and steps");
            }

        });
    }

    public void displayFractal(){
        try{

            int stepsValue = Integer.parseInt(steps.getText().trim());
            if (stepsValue > 0) {
                FractalDisplayObserver chaosGameObserver = new FractalDisplayObserver(chaosGame, stepsValue, width, height);
                chaosGame.attach(chaosGameObserver);
                chaosGame.notifyObservers();
                imageView = chaosGameObserver.getFractalImageView();
                root.setCenter(imageView);
            } else {
                throw new NumberFormatException("Steps has to be a positive number");
            }

        } catch(NumberFormatException e) {
            errorLabel.setText("Steps has to be a whole positive number");
        }
        catch (RuntimeException e) {
            errorLabel.setText("Steps cannot be larger than 100 000 000");
        }
    }

    public void saveAsImage() throws IOException {
        WritableImage writableImage = new WritableImage(height, width);
        imageView.snapshot(null, writableImage);

        PixelReader pixelReader = writableImage.getPixelReader();
        BufferedImage bufferedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                bufferedImage.setRGB(x, y, pixelReader.getArgb(x, y));
            }
        }

        try {
            if (getChaosGameDescription() == null) {
                throw new IllegalArgumentException("No fractal to save");
            }else {
                //User selects file path
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save image File");
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showSaveDialog(null);

                if (file != null) {
                    imageFileText.setText(file.getAbsolutePath());
                    imageFileText.setText("File saved to " + imageFileText.getText());

                    ImageIO.write(bufferedImage, "png", file);
                }
                else {
                    imageFileText.setText("No path selected");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setChaosGame(ChaosGame chaosGame){
        this.chaosGame = chaosGame;
    }

    public ChaosGameDescription getChaosGameDescription() {
        return chaosGameDescription;
    }

    public void setChaosGameDescription(ChaosGameDescription chaosGameDescription) {
        this.chaosGameDescription = chaosGameDescription;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Button getShowFractal() {
        return showFractal;
    }

    public Vector2D getMinCoords(){
        return new Vector2D(Double.parseDouble(minCoordX0.getText()), Double.parseDouble(minCoordX1.getText()));
    }

    public Vector2D getMaxCoords(){
        return new Vector2D(Double.parseDouble(maxCoordX0.getText()), Double.parseDouble(maxCoordX1.getText()));
    }

    public void setAffineCoords(){
        minCoordX0.setText("0");
        minCoordX1.setText("0");
        maxCoordX0.setText("1");
        maxCoordX1.setText("1");
    }

    public void setBarnsleyCoords(){
        minCoordX0.setText("-2.65");
        minCoordX1.setText("0");
        maxCoordX0.setText("2.65");
        maxCoordX1.setText("10");
    }

    public void setJuliaCoord(){
        minCoordX0.setText("-1.6");
        minCoordX1.setText("-1");
        maxCoordX0.setText("1.6");
        maxCoordX1.setText("1.1");
    }

    @Override
    public void start(Stage stage){
        this.stage = stage;
        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add("/css/GameStyles.css");

        stage.setTitle(pageTitle);
        stage.setScene(scene);
        stage.show();
    }
}
