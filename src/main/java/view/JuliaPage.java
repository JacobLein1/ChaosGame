package view;

import controller.ChaosGame;
import controller.ChaosGameDescriptionFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JuliaPage extends Application {
    private Stage juliaStage;
    private BorderPane root;
    private ChaosGame chaosGame;
    private final int width = 500;
    private final int height = 600;
    private TextField stepsBox;

    public JuliaPage() {
        root = new BorderPane();
        setMenu();
    }

    @Override
    public void start(Stage juliaStage) throws Exception {
        this.juliaStage = juliaStage;

        Scene scene = new Scene(root, 1000, 800);

        juliaStage.setTitle("Affine2D");
        juliaStage.setScene(scene);
        juliaStage.show();
    }

    public void setMenu(){
        Label header = new Label("Julia");
        Label numberLabel = new Label("Number of steps:");

        Button home = new Button("Home");
        home.setOnAction(actionEvent -> {
            HomePage homePage = new HomePage();
            homePage.start(juliaStage);
        });

        Button showFractal = new Button("Show");
        showFractal.setOnAction(actionEvent -> {
            try {
                chaosGame = new ChaosGame(ChaosGameDescriptionFactory.get("Julia"), width, height);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            displayFractal();
        });

        stepsBox = new TextField();

        HBox menu = new HBox(header, numberLabel, stepsBox, showFractal, home);
        HBox.setMargin(header, new Insets(20, 20, 10, 10));
        HBox.setMargin(numberLabel, new Insets(20, 20, 10, 10));
        HBox.setMargin(stepsBox, new Insets(20, 10, 20, 10));
        HBox.setMargin(showFractal, new Insets(20, 10, 10, 20));
        HBox.setMargin(home, new Insets(20, 10, 10, 20));

        root.setTop(menu);
    }

    public void displayFractal(){
        InitializeChaosGame initializeChaosGame = new InitializeChaosGame(chaosGame, Integer.parseInt(stepsBox.getText()), width, height);
        ImageView image = initializeChaosGame.createFractalDisplay();
        root.setCenter(image);
    }
}
