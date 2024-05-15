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

/**
 * The type Affine 2 d page.
 */
public class Affine2DPage extends Application {
    private Stage affine2DStage;
    private final BorderPane root;
    private ChaosGame chaosGame;
    private final int width = 500;
    private final int height = 600;
    private TextField stepsBox;

    /**
     * Instantiates a new Affine 2 d page.
     */
    public Affine2DPage() {
        root = new BorderPane();
        setMenu();
    }

    @Override
    public void start(Stage affine2DStage) throws Exception {
        this.affine2DStage = affine2DStage;

        Scene scene = new Scene(root, 1000, 800);

        affine2DStage.setTitle("Affine2D");
        affine2DStage.setScene(scene);
        affine2DStage.show();
    }

    /**
     * Set menu.
     */
    public void setMenu(){
        Label header = new Label("Sierpinski");
        Label numberLabel = new Label("Number of steps:");

        Button home = new Button("Home");
        home.setOnAction(actionEvent -> {
            HomePage homePage = new HomePage();
            homePage.start(affine2DStage);
        });

        Button showFractal = new Button("Show");
        showFractal.setOnAction(actionEvent -> {
            try {
                chaosGame = new ChaosGame(ChaosGameDescriptionFactory.get("Affine2D"), width, height);
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

    /**
     * Display fractal.
     */
    public void displayFractal(){
        InitializeChaosGame initializeChaosGame = new InitializeChaosGame(chaosGame, Integer.parseInt(stepsBox.getText()), width, height);
        ImageView image = initializeChaosGame.createFractalDisplay();
        root.setCenter(image);
    }
}
