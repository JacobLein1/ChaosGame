package view;

import controller.ChaosGame;
import controller.ChaosGameDescription;
import controller.ChaosGameFileHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

/**
 * The type Upload file page.
 */
public class UploadFilePage extends Application {
    private ChaosGameDescription chaosGameDescription;
    private ChaosGame chaosGame;
    private ChaosGameFileHandler chaosGameFileHandler;
    private BorderPane root;

    /**
     * Instantiates a new Upload file page.
     */
    public UploadFilePage() {

    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    /**
     * Set open file menu.
     */
    public void setOpenFileMenu(){
        Button openFile = new Button("Open file");
        openFile.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(null);
            if (file != null){
                try {
                    chaosGameDescription = chaosGameFileHandler.readTransformationFile(file.getAbsolutePath());
                    chaosGame = new ChaosGame(chaosGameDescription, 100, 100);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException("File not found");
                }
            }
        });
        VBox openFileMenu = new VBox(openFile);
        openFileMenu.setSpacing(5);
        openFileMenu.setPadding(new Insets(10,10,10,10));
        root.setLeft(openFileMenu);
    }
}
