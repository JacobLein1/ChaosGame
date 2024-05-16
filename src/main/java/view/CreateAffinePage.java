package view;

import controller.ChaosGame;
import controller.ChaosGameDescription;
import controller.ChaosGameFileHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AffineTransform2D;
import model.Matrix2x2;
import model.Transform2D;
import model.Vector2D;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateAffinePage extends Application {
    private Stage createAffineStage;
    private final BorderPane root;
    private ChaosGame chaosGame;
    private ChaosGameDescription chaosGameDescription;
    private List<Transform2D> newTransformations = new ArrayList<>();
    private final int width = 500;
    private final int height = 600;
    private TextField stepsBox;
    private final ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
    private Text fileText;
    private AffineTransform2D affineTransform2D;

    public CreateAffinePage() {
        root = new BorderPane();
        setMenu();
        setNewTransformationMenu();
    }

    @Override
    public void start(Stage createAffineStage) throws Exception {
        this.createAffineStage = createAffineStage;

        Scene scene = new Scene(root, 1000, 800);

        createAffineStage.setTitle("Create Affine transformation");
        createAffineStage.setScene(scene);
        createAffineStage.show();
    }

    public void setMenu(){
        Label header = new Label("Create Affine transformation");
        Label numberLabel = new Label("Number of steps:");

        Button home = new Button("Home");
        home.setOnAction(actionEvent -> {
            HomePage homePage = new HomePage();
            homePage.start(createAffineStage);
        });

        Button showFractal = new Button("Show");
        showFractal.setOnAction(actionEvent -> {
            chaosGameDescription = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(1,1), newTransformations);
            chaosGame = new ChaosGame(chaosGameDescription, width, height);
            displayFractal();
            newTransformations = new ArrayList<>();
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

    public void setNewTransformationMenu(){
        Label matrixLabel = new Label("Matrix");
        TextField matrix00 = new TextField();
        TextField matrix01 = new TextField();
        TextField matrix10 = new TextField();
        TextField matrix11 = new TextField();
        matrix00.setPromptText("00");
        matrix01.setPromptText("01");
        matrix10.setPromptText("10");
        matrix11.setPromptText("11");
        Label vectorLabel = new Label("Vector");
        TextField vectorX1 = new TextField();
        TextField vectorX0 = new TextField();
        vectorX0.setPromptText("X0");
        vectorX1.setPromptText("X1");

        Button saveTransformation = new Button("Save transformation");
        saveTransformation.setOnAction(actionEvent -> {
             affineTransform2D = new AffineTransform2D(
                new Matrix2x2(
                    Double.parseDouble(matrix00.getText()),
                    Double.parseDouble(matrix01.getText()),
                    Double.parseDouble(matrix10.getText()),
                    Double.parseDouble(matrix11.getText())),
                new Vector2D(
                    Double.parseDouble(vectorX0.getText()),
                    Double.parseDouble(vectorX1.getText())));
            newTransformations.add(affineTransform2D);
            matrix00.clear();
            matrix01.clear();
            matrix10.clear();
            matrix11.clear();
            vectorX0.clear();
            vectorX1.clear();
            chaosGameDescription = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(1,1), newTransformations);
        });

        VBox saveAsFileBox = new VBox();
        fileText = new Text("No path selected");
        Button saveAsFile = new Button("Save as file");
        saveAsFileBox.getChildren().addAll(saveAsFile, fileText);
        saveAsFileBox.setPadding(new Insets(20, 10, 10, 20));


        saveAsFile.setOnAction(actionEvent -> {

            try {
                if (chaosGameDescription == null) {
                    throw new IllegalArgumentException("No transformation to save");
                }else {
                    //User selects file path
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save Transformation File");
                    File file = fileChooser.showSaveDialog(createAffineStage);

                    if (file != null) {
                        fileText.setText(file.getAbsolutePath());
                        System.out.println("Path chosen");

                        chaosGameFileHandler.writeToFile(chaosGameDescription, file.getAbsolutePath());
                        fileText.setText("File saved to " + fileText.getText());
                    }
                    else {
                        fileText.setText("No path selected");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        VBox newTransformationMenu = new VBox(
                vectorLabel, vectorX0, vectorX1,
                matrixLabel, matrix00, matrix01, matrix10, matrix11,
                saveTransformation, saveAsFileBox);
        newTransformationMenu.setSpacing(5);
        newTransformationMenu.setPadding(new Insets(0, 0, 0, 10));
        VBox.setMargin(vectorX1, new Insets(5,0,15, 0));
        VBox.setMargin(matrix11, new Insets(5,0,15,0));
        VBox.setMargin(saveTransformation, new Insets(5,0,15, 0));
        root.setLeft(newTransformationMenu);
    }

    public void displayFractal(){
        InitializeChaosGame initializeChaosGame = new InitializeChaosGame(chaosGame, Integer.parseInt(stepsBox.getText()), width, height);
        ImageView image = initializeChaosGame.createFractalDisplay();
        root.setCenter(image);
    }
}
