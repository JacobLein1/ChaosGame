package view;

import controller.ChaosGame;
import controller.ChaosGameDescription;
import controller.ChaosGameDescriptionFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AffineTransform2D;
import model.Matrix2x2;
import model.Transform2D;
import model.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Application{
    private final BorderPane root = new BorderPane();
    private HBox menu;
    private ComboBox<String> fractalType;
    private Text display;
    private TextField stepsBox;
    private Button showFractal;
    private ChaosGame chaosGame;
    private ChaosGameDescription chaosGameDescription;
    private TextField vectorX0;
    private TextField vectorX1;
    private TextField matrix00;
    private TextField matrix01;
    private TextField matrix10;
    private TextField matrix11;
    private List<Transform2D> newTransformations = new ArrayList<>();

    public HomePage(){
        HBox bottom = new HBox();
        bottom.setPrefSize(50, 50);
        root.setBottom(bottom);

        setMenu();
        root.setTop(menu);

        showFractal.setOnAction(actionEvent ->
            showFractalOnAction()
        );
    }

    public void setMenu(){
        String[] fractals = {"Affine2D", "Barnsley", "Julia", "Create new Affine transformation", "Create new Barnsley transformation", "Upload from files"};
        fractalType = new ComboBox<>(FXCollections.observableArrayList(fractals));
        Label label = new Label("Number of steps:");
        showFractal = new Button("Show");

        stepsBox = new TextField();

        menu = new HBox();
        menu.getStyleClass().add("background-color");
        menu.setSpacing(50);
        menu.getChildren().addAll(fractalType, label, stepsBox, showFractal);
        HBox.setMargin(fractalType, new Insets(20, 10, 10, 20));
        HBox.setMargin(label, new Insets(20, 20, 10, 10));
        HBox.setMargin(stepsBox, new Insets(20, 10, 20, 10));
        HBox.setMargin(showFractal, new Insets(20, 10, 10, 20));
    }

    public void setNewTransformationMenu(){
        Label matrixLabel = new Label("Matrix");
        matrix00 = new TextField();
        matrix01 = new TextField();
        matrix10 = new TextField();
        matrix11 = new TextField();
        matrix00.setPromptText("00");
        matrix01.setPromptText("01");
        matrix10.setPromptText("10");
        matrix11.setPromptText("11");
        Label vectorLabel = new Label("Vector");
        vectorX1 = new TextField();
        vectorX0 = new TextField();
        vectorX0.setPromptText("X0");
        vectorX1.setPromptText("X1");

        Button saveTransformation = new Button("Save transformation");
        saveTransformation.setOnAction(actionEvent ->
                saveNewTransformation()
        );

        Button saveAsFile = new Button("Save as file");

        Separator sep1 = new Separator();
        Separator sep2 = new Separator();
        Separator sep3 = new Separator();
        sep1.setPadding(new Insets(0,0,10,0));
        sep2.setPadding(new Insets(0,0,10,0));
        sep3.setPadding(new Insets(0,0,10,0));

        VBox newTransformationMenu = new VBox(
                vectorLabel, vectorX0, vectorX1, sep1,
                matrixLabel, matrix00, matrix01, matrix10, matrix11, sep2,
                saveTransformation, sep3, saveAsFile);
        newTransformationMenu.setSpacing(5);
        newTransformationMenu.setPadding(new Insets(10,10,10,10));
        root.setLeft(newTransformationMenu);
    }

    public void saveNewTransformation(){
        AffineTransform2D affineTransform2D = new AffineTransform2D(
                new Matrix2x2(
                        Double.parseDouble(matrix00.getText()),
                        Double.parseDouble(matrix01.getText()),
                        Double.parseDouble(matrix10.getText()),
                        Double.parseDouble(matrix11.getText())),
                new Vector2D(
                        Double.parseDouble(vectorX0.getText()),
                        Double.parseDouble(vectorX1.getText()))
        );
        newTransformations.add(affineTransform2D);
        matrix00.clear();
        matrix01.clear();
        matrix10.clear();
        matrix11.clear();
        vectorX0.clear();
        vectorX1.clear();
    }

    public void removeNewTransformationMenu(){
        root.setLeft(null);
    }

    public void initChaosGame(){
        try{
            int steps = Integer.parseInt(stepsBox.getText().trim());
            if(steps < 100000000){
                chaosGame.runSteps(steps);
            }else{
                throw new RuntimeException("The number is too high");
            }
        } catch (NumberFormatException n){
            throw new IllegalArgumentException("The input must be a number");
        }
    }

    public void setFractalDisplay(){
        initChaosGame();
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

    public void showFractalOnAction(){
        if(fractalType.getValue().equals("Julia")){
            removeNewTransformationMenu();
            chaosGame = new ChaosGame(ChaosGameDescriptionFactory.julia(), 100, 100);
        }
        if(fractalType.getValue().equals("Affine2D") || fractalType.getValue().equals("Barnsley")){
            removeNewTransformationMenu();
            try {
                chaosGame = new ChaosGame(ChaosGameDescriptionFactory.get(fractalType.getValue()), 100, 100);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(fractalType.getValue().equals("Create new Affine transformation")){
            setNewTransformationMenu();
            chaosGameDescription = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(1,1), newTransformations);
            chaosGame = new ChaosGame(chaosGameDescription, 100, 100);
        }
        if(fractalType.getValue().equals("Create new Barnsley transformation")){
            setNewTransformationMenu();
            chaosGameDescription = new ChaosGameDescription(new Vector2D(-2.1820, 0), new Vector2D(2.6558, 9.9983), newTransformations);
            chaosGame = new ChaosGame(chaosGameDescription, 100, 100);
        }
        //observer her
        setFractalDisplay();
        root.setCenter(display);
        newTransformations = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) {
        root.setTop(menu);
        root.setCenter(display);
        Scene scene = new Scene(root, 1000, 800);

        // Set up the stage
        stage.setTitle("Chaos Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        HomePage.launch(args);
    }
}
