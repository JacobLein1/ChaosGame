package view;

import controller.ChaosGame;
import controller.ChaosGameDescription;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.AffineTransform2D;
import model.Matrix2x2;
import model.Transform2D;
import model.Vector2D;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class HomePage extends Application{
    private BorderPane root = new BorderPane();
    private HBox menu;
    private Text display;
    private TextField stepsBox;
    private String stepsInput;
    private Button showDisplay;
    private ChaosGame chaosGame;

    public HomePage(){
        showDisplay = new Button("Show");

        HBox bottom = new HBox();
        bottom.setPrefSize(50, 50);
        root.setBottom(bottom);

        List<Transform2D> transformation = new ArrayList<>();
        transformation.add(new AffineTransform2D(new Matrix2x2(0,0,0,0), new Vector2D(100, 100)));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100, 100), transformation);

        setMenu();
        root.setTop(menu);

        showDisplay.setOnAction(actionEvent -> {
            chaosGame = new ChaosGame(chaosGameDescription, 100, 100);
            stepsInput = stepsBox.getText();
            initChaosGame();
            setDisplay();
            root.setCenter(display);
        });
    }

    public void setMenu(){
        Label label = new Label("Number of steps:");
        stepsBox = new TextField();
        stepsInput = stepsBox.getText();

        menu = new HBox();
        menu.setSpacing(50);
        menu.getChildren().addAll(label, stepsBox, showDisplay);
        HBox.setMargin(label, new Insets(20, 20, 10, 10));
        HBox.setMargin(stepsBox, new Insets(20, 10, 20, 10));
        HBox.setMargin(showDisplay, new Insets(20, 10, 10, 20));
    }

    public void setDisplay(){
        display = new Text();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chaosGame.getCanvas().getCanvasArray().length; i++) {
            for (int j = 0; j < chaosGame.getCanvas().getCanvasArray()[i].length; j++) {
                if (chaosGame.getCanvas().getCanvasArray()[i][j] == 1) {
                    result.append("*");
                } else {
                    result.append("  ");
                }
            }
            result.append("\n");
        }
        display.setText(result.toString());
        display.setFont(new Font(6));
    }

    public void initChaosGame(){
        try{
            int steps = Integer.parseInt(stepsInput.trim());
            if(steps < 100000000){
                chaosGame.runSteps(steps);
            }else{
                throw new RuntimeException("The number is too high");
            }

        } catch (NumberFormatException n){
            throw new IllegalArgumentException("The input must be a number");
        }
    }

    @Override
    public void start(Stage stage) {
        root.setTop(menu);
        root.setCenter(display);
        Scene scene = new Scene(root, 700, 800);

        // Set up the stage
        stage.setTitle("Chaos Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        HomePage.launch(args);
    }
}
