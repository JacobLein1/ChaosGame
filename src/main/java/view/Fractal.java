package view;

import controller.ChaosGame;
import controller.ChaosGameDescription;
import controller.FractalDisplayObserver;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Vector2D;

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
    private FractalDisplayObserver fractalDisplayObserver;

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

        minCoordX0.setPromptText("X0");
        minCoordX1.setPromptText("X1");
        maxCoordX0.setPromptText("X0");
        maxCoordX1.setPromptText("X1");

        numberLabel.getStyleClass().add("menu-label");
        steps.getStyleClass().add("steps-button");
        showFractal.getStyleClass().add("big-menu-button");
        home.getStyleClass().add("big-menu-button");
        minCoordLabel.getStyleClass().add("menu-label");
        minCoordX0.getStyleClass().add("menu-button");
        minCoordX1.getStyleClass().add("menu-button");
        maxCoordLabel.getStyleClass().add("menu-label");
        maxCoordX0.getStyleClass().add("menu-button");
        maxCoordX1.getStyleClass().add("menu-button");

        home.setOnAction(actionEvent -> {
            HomePage homePage = new HomePage();
            homePage.start(stage);
        });

        VBox minVector = new VBox(minCoordX0, minCoordX1);
        VBox maxVector = new VBox(maxCoordX0, maxCoordX1);
        VBox.setMargin(minCoordX1, new Insets(5,0,10,0));
        VBox.setMargin(maxCoordX0, new Insets(0,0,5,0));
        VBox.setMargin(maxCoordX1, new Insets(0,0,5,0));
        HBox vectorBox = new HBox(minCoordLabel, minVector, maxCoordLabel, maxVector);
        HBox stepsBox = new HBox(numberLabel, steps);
        HBox.setMargin(steps, new Insets(10,0,20,34));

        VBox inputBoxes = new VBox(stepsBox, vectorBox);
        HBox buttonBox = new HBox(showFractal, home);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);

        HBox menu = new HBox(inputBoxes, buttonBox);
        HBox.setMargin(buttonBox, new Insets(0,0,0,250));
        HBox.setMargin(numberLabel, new Insets(20, 20, 10, 10));
        HBox.setMargin(stepsBox, new Insets(20, 10, 20, 10));
        HBox.setMargin(minCoordLabel, new Insets(20, 20, 10, 10));
        HBox.setMargin(maxCoordLabel, new Insets(20, 20, 10, 10));
        HBox.setMargin(showFractal, new Insets(20, 10, 10, 20));
        HBox.setMargin(home, new Insets(20, 10, 10, 20));
        menu.getStyleClass().add("menu-background");

        root.setTop(menu);
        root.getStyleClass().add("background");
    }

    public void showFractalOnAction(){
        showFractal.setOnAction(actionEvent -> {
            chaosGameDescription.setMinCoords(getMinCoords());
            chaosGameDescription.setMaxCoords(getMaxCoords());
            try {
                chaosGame = new ChaosGame(chaosGameDescription, width, height);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            displayFractal();
        });
    }

    public void displayFractal(){
        fractalDisplayObserver = new FractalDisplayObserver(chaosGame, Integer.parseInt(steps.getText()), width, height);
        fractalDisplayObserver.updateGame();
        root.setCenter(fractalDisplayObserver.getFractalImageView());
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

    public void setChaosGameDescription(ChaosGameDescription chaosGameDescription) {
        this.chaosGameDescription = chaosGameDescription;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Vector2D getMinCoords(){
        return new Vector2D(Double.parseDouble(minCoordX0.getText()), Double.parseDouble(minCoordX1.getText()));
    }

    public Vector2D getMaxCoords(){
        return new Vector2D(Double.parseDouble(maxCoordX0.getText()), Double.parseDouble(maxCoordX1.getText()));
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
