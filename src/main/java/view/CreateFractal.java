package view;

import controller.ChaosGameDescription;
import controller.ChaosGameFileHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateFractal extends Fractal{
    private final List<Transform2D> newTransformations = new ArrayList<>();
    //private Vector2D minCoords = new Vector2D(0,0);
    //private Vector2D maxCoords = new Vector2D(1,1);
    private final ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
    private Text fileText;


    public void setNewTransformationMenu(){
        setMenu();
        showFractalOnAction();
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
            AffineTransform2D affineTransform2D = new AffineTransform2D(
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
        });

        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0), new Vector2D(1, 1), newTransformations);
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
                    File file = fileChooser.showSaveDialog(null);

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
        getRoot().setLeft(newTransformationMenu);
    }

    /**
     * Set new julia transformation menu.
     */
    public void setNewJuliaTransformationMenu(){
        setMenu();

        /**
        VBox minCoordsBox = new VBox();
        Label minCoordsLabel = new Label("Min coords:");

        Text savedMinCoords = new Text();
        TextField minCoordsX0 = new TextField();
        TextField minCoordsX1 = new TextField();
        minCoordsX0.setPromptText("X0");
        minCoordsX1.setPromptText("X1");

        minCoordsBox.getChildren().addAll(minCoordsLabel, minCoordsX0, minCoordsX1, savedMinCoords);

        Label maxCoordsLabel = new Label("Max coords:");
        VBox maxCoordsBox = new VBox();
        Text savedMaxCoords = new Text();
        TextField maxCoordsX0 = new TextField();
        TextField maxCoordsX1 = new TextField();
        maxCoordsX0.setPromptText("X0");
        maxCoordsX1.setPromptText("X1");

        maxCoordsBox.getChildren().addAll(maxCoordsLabel, maxCoordsX0, maxCoordsX1, savedMaxCoords);

        Label coordsLabel = new Label("Coordinates");
        VBox coordsBox = new VBox();
        coordsBox.setSpacing(5);
        coordsBox.getChildren().addAll(minCoordsBox, maxCoordsBox);
        */

        Label realLabel = new Label("Real part");
        VBox realPartBox = new VBox();

        Text errorTextReal = new Text();
        HBox reaPartTextFieldBox = new HBox();
        TextField realPart = new TextField();
        reaPartTextFieldBox.getChildren().addAll(realPart,errorTextReal);

        Text savedRealPart = new Text("No saved real part");
        realPart.setPromptText("Real part");
        realPartBox.getChildren().addAll(reaPartTextFieldBox, savedRealPart);

        Label imaginaryLabel = new Label("Imaginary part");
        VBox imaginaryPartBox = new VBox();

        TextField imaginaryPart = new TextField();
        HBox imaginaryPartTextFieldBox = new HBox();
        Text errorTextImaginary = new Text();
        imaginaryPartTextFieldBox.getChildren().addAll(imaginaryPart, errorTextImaginary);
        Text savedImaginaryPart = new Text("No saved imaginary part");
        imaginaryPart.setPromptText("Imaginary part");
        imaginaryPartBox.getChildren().addAll(imaginaryPart, savedImaginaryPart);

        Button saveTransformation = new Button("Save julia transformation");
        saveTransformation.setOnAction(actionEvent -> {

            JuliaTransform juliaTransformPos = new JuliaTransform(
                    new Complex(
                            Double.parseDouble(realPart.getText()),
                            Double.parseDouble(imaginaryPart.getText())),
                    1);
            JuliaTransform juliaTransformNeg = new JuliaTransform(
                    new Complex(
                            Double.parseDouble(realPart.getText()),
                            Double.parseDouble(imaginaryPart.getText())),
                    -1);
            newTransformations.add(juliaTransformPos);
            newTransformations.add(juliaTransformNeg);
            savedRealPart.setText("Saved real part: " + realPart.getText());
            savedImaginaryPart.setText("Saved imaginary part: " + imaginaryPart.getText());
            //savedMinCoords.setText("Saved min coords: " + minCoordsX0.getText() + ", " + minCoordsX1.getText());
            //savedMaxCoords.setText("Saved max coords: " + maxCoordsX0.getText() + ", " + maxCoordsX1.getText());
            System.out.println("HER");
            //minCoords = new Vector2D(Double.parseDouble(minCoordsX0.getText()), Double.parseDouble(minCoordsX1.getText()));
            //maxCoords = new Vector2D(Double.parseDouble(maxCoordsX0.getText()), Double.parseDouble(maxCoordsX1.getText()));

            realPart.clear();
            imaginaryPart.clear();
            //minCoordsX0.clear();
            //minCoordsX1.clear();
            //maxCoordsX0.clear();
            //maxCoordsX1.clear();

        });
        /**VBox newTransformationMenu = new VBox(
                coordsLabel, coordsBox ,realLabel, realPartBox,
                imaginaryLabel, imaginaryPartBox,
                saveTransformation);
         */
        VBox newTransformationMenu = new VBox(
                realLabel, realPartBox,
                imaginaryLabel, imaginaryPartBox,
                saveTransformation);
        newTransformationMenu.setSpacing(5);
        newTransformationMenu.setPadding(new Insets(0, 0, 0, 10));
        VBox.setMargin(imaginaryPart, new Insets(5,0,15, 0));
        VBox.setMargin(realPart, new Insets(5,0,15, 0));
        VBox.setMargin(saveTransformation, new Insets(5,0,15, 0));
        getRoot().setLeft(newTransformationMenu);

    }

    public List<Transform2D> getNewTransformations() {
        return newTransformations;
    }

    /**
    public Vector2D getMinCoords() {
        return minCoords;
    }
    public Vector2D getMaxCoords() {
        return maxCoords;
    }
     */

}
