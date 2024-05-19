package view;

import controller.ChaosGame;
import controller.ChaosGameDescription;
import controller.FractalDisplayObserver;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Fractal extends Application {
    private Stage stage;
    private TextField stepsBox;
    private String pageTitle;
    private final BorderPane root = new BorderPane();
    private ChaosGame chaosGame;
    private ChaosGameDescription chaosGameDescription;
    private int width;
    private int height;
    private FractalDisplayObserver fractalDisplayObserver;

    public void setMenu(){
        Label numberLabel = new Label("Number of steps:");

        stepsBox = new TextField();

        Button showFractal = new Button("Show");
        showFractal.setOnAction(actionEvent -> {
            try {
                chaosGame = new ChaosGame(chaosGameDescription, width, height);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            displayFractal();
        });

        Button home = new Button("Home");
        home.setOnAction(actionEvent -> {
            HomePage homePage = new HomePage();
            homePage.start(stage);
        });

        HBox menu = new HBox(numberLabel, stepsBox, showFractal, home);
        HBox.setMargin(numberLabel, new Insets(20, 20, 10, 10));
        HBox.setMargin(stepsBox, new Insets(20, 10, 20, 10));
        HBox.setMargin(showFractal, new Insets(20, 10, 10, 20));
        HBox.setMargin(home, new Insets(20, 10, 10, 20));

        root.setTop(menu);
    }

    public void displayFractal(){
        fractalDisplayObserver = new FractalDisplayObserver(chaosGame, Integer.parseInt(stepsBox.getText()), width, height);
        fractalDisplayObserver.updateGame();
        root.setCenter(fractalDisplayObserver.getFractalImageView());
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public BorderPane getRoot() {
        return root;
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

    @Override
    public void start(Stage stage){
        this.stage = stage;
        Scene scene = new Scene(root, 1000, 800);

        stage.setTitle(pageTitle);
        stage.setScene(scene);
        stage.show();
    }
}
