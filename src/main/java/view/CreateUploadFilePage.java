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
public class CreateUploadFilePage extends Fractal {

    private final ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
    private final int width = 500;
    private final int height = 600;
    private File file;

    /**
     * Instantiates a new Upload file page.
     */
    public CreateUploadFilePage() {
        setPageTitle("Upload file");

        setMenu();
        uploadFileButtonsOnAction();
    }

    public void uploadFileButtonsOnAction(){
        Button showFractal = new Button("Show");
        showFractal.setOnAction(actionEvent -> {
            try {
                ChaosGameDescription chaosGameDescription = chaosGameFileHandler.readTransformationFile(file.getAbsolutePath());
                setChaosGame(new ChaosGame(chaosGameDescription, width, height));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            displayFractal();
        });

        VBox openFileBox = new VBox();
        Button openFile = new Button("Open file");
        Text fileText = new Text("No file selected");
        openFileBox.getChildren().addAll(openFile, fileText);
        openFileBox.setPadding(new Insets(20, 10, 10, 20));
        VBox.setMargin(fileText, new Insets(10,0,0,0));

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

        getRoot().setLeft(openFileBox);
    }
}

