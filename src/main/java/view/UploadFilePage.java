package view;

import model.ChaosGame;
import controller.ChaosGameDescription;
import controller.ChaosGameFileHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.text.Text;


import java.io.File;

/**
 * This class displays the page where the user can upload a file with values for a fractal.
 */
public class UploadFilePage extends Fractal {

    private final ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
    private final int width = 500;
    private final int height = 600;
    private File file;

    /**
     * Instantiates a new Upload file page.
     */
    public UploadFilePage() {
        setPageTitle("Upload file");
        setWidth(500);
        setHeight(600);

        setMenu();
        uploadFileButtonsOnAction(); //action for "Upload file" button
    }

    //action for "Upload file" button
    public void uploadFileButtonsOnAction(){
        getShowFractal().setOnAction(actionEvent -> {
            try {
                setChaosGame(new ChaosGame(getChaosGameDescription(), width, height));
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

        //action for "Open file" button
        openFile.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            file = fileChooser.showOpenDialog(null);
            if (file != null){
                try {
                    fileText.setText(file.getName());
                    setChaosGameDescription(chaosGameFileHandler.readTransformationFile(file.getAbsolutePath())); //reads file and sets new chaosGameDescription
                    setMinCoords(getChaosGameDescription().getMinCoords());
                    setMaxCoords(getChaosGameDescription().getMaxCoords());
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

