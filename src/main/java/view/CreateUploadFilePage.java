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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;


import java.io.File;

/**
 * The type Upload file page.
 */
public class CreateUploadFilePage extends Application {
    private Stage uploadFileStage;
    private final BorderPane root;
    private ChaosGameDescription chaosGameDescription;
    private ChaosGame chaosGame;
    private ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();

    private TextField stepsBox;
    private final int width = 500;
    private final int height = 600;
    private File file;
    private Text fileText;

    /**
     * Instantiates a new Upload file page.
     */
    public CreateUploadFilePage() {
    root = new BorderPane();
    setMenu();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.uploadFileStage = stage;

        Scene scene = new Scene(root, 1000, 800);
        uploadFileStage.setScene(scene);
        uploadFileStage.show();
    }

    /**
     * Set open file menu.
     */
    public void setMenu(){

        Label header = new Label("Upload file");
        Label numberLabel = new Label("Number of steps:");
        Button home = new Button("Home");
        home.setOnAction(actionEvent -> {
            HomePage homePage = new HomePage();
            homePage.start(uploadFileStage);
        });

        Button showFractal = new Button("Show");
        showFractal.setOnAction(actionEvent -> {
            try {
                chaosGameDescription = chaosGameFileHandler.readTransformationFile(file.getAbsolutePath());
                chaosGame = new ChaosGame(chaosGameDescription, width, height);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            displayFractal();
        });
        VBox openFileBox = new VBox();
        Button openFile = new Button("Open file");
        fileText = new Text("No file selected");
        openFileBox.getChildren().addAll(openFile, fileText);
        openFileBox.setPadding(new Insets(20, 10, 10, 20));

        openFile.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            file = fileChooser.showOpenDialog(null);
            if (file != null){
                try {
                    fileText.setText(file.getName());
                    System.out.println(file.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException("File not found");
                }
            }
            else {
                System.out.println("File is null");
            }
        });

        stepsBox = new TextField();
        HBox menu = new HBox(header, numberLabel, stepsBox, openFileBox, showFractal, home);
        HBox.setMargin(header, new Insets(20, 20, 10, 10));
        HBox.setMargin(numberLabel, new Insets(20, 20, 10, 10));
        HBox.setMargin(stepsBox, new Insets(20, 10, 20, 10));
        HBox.setMargin(openFile, new Insets(20, 10, 10, 20));
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
