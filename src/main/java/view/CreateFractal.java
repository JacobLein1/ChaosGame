package view;

import controller.ChaosGame;
import controller.ChaosGameFileHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateFractal extends Fractal{
    private List<Transform2D> newTransformations = new ArrayList<>();
    private final ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
    private Text fileText;
    private Button saveAsFile;
    private Label errorLabel;
    private Fractal fractal;

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
        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        matrixLabel.getStyleClass().add("sidebar-label");
        matrix00.getStyleClass().add("menu-field");
        matrix01.getStyleClass().add("menu-field");
        matrix10.getStyleClass().add("menu-field");
        matrix11.getStyleClass().add("menu-field");
        vectorLabel.getStyleClass().add("sidebar-label");
        vectorX0.getStyleClass().add("menu-field");
        vectorX1.getStyleClass().add("menu-field");
        saveTransformation.getStyleClass().add("big-sidebar-button");

        saveTransformation.setOnAction(actionEvent -> {
            try{
                if (matrix00.getText().isEmpty() || matrix01.getText().isEmpty() || matrix10.getText().isEmpty() || matrix11.getText().isEmpty() || vectorX0.getText().isEmpty() || vectorX1.getText().isEmpty()){
                    errorLabel.setText("Please fill in all fields");
                    return;
                }
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
                errorLabel.setText("");
            } catch (NumberFormatException e){
                errorLabel.setText("Please enter valid numbers in all fields");
            }
            catch (Exception e){
                errorLabel.setText("An unexpected error occurred");
            }
        });

        VBox saveAsFileBox = new VBox();
        fileText = new Text("No path selected");
        saveAsFile = new Button("Save as file");
        saveAsFileBox.getChildren().addAll(saveAsFile, fileText);
        saveAsFile.getStyleClass().add("big-sidebar-button");
        saveAsFileBox.setPadding(new Insets(20, 0, 10, 0));
        saveAsFile();

        VBox newTransformationMenu = new VBox(
                vectorLabel, vectorX0, vectorX1,
                matrixLabel, matrix00, matrix01, matrix10, matrix11,
                saveTransformation, errorLabel, saveAsFileBox);
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

        Label realLabel = new Label("Real part");
        VBox realPartBox = new VBox();


        HBox reaPartTextFieldBox = new HBox();
        TextField realPart = new TextField();
        reaPartTextFieldBox.getChildren().add(realPart);

        Text savedRealPart = new Text("No saved real part");
        realPart.setPromptText("Real part");
        realPartBox.getChildren().addAll(reaPartTextFieldBox, savedRealPart);

        Label imaginaryLabel = new Label("Imaginary part");
        VBox imaginaryPartBox = new VBox();

        TextField imaginaryPart = new TextField();
        HBox imaginaryPartTextFieldBox = new HBox();
        imaginaryPartTextFieldBox.getChildren().add(imaginaryPart);
        Text savedImaginaryPart = new Text("No saved imaginary part");
        imaginaryPart.setPromptText("Imaginary part");
        imaginaryPartBox.getChildren().addAll(imaginaryPart, savedImaginaryPart);

        Button saveTransformation = new Button("Save julia transformation");
        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red;");

        realLabel.getStyleClass().add("sidebar-label");
        realPart.getStyleClass().add("menu-field");
        imaginaryLabel.getStyleClass().add("sidebar-label");
        imaginaryPart.getStyleClass().add("menu-field");
        VBox.setMargin(savedRealPart, new Insets(5,0,10,0));
        VBox.setMargin(savedImaginaryPart, new Insets(0,0,10,0));
        saveTransformation.getStyleClass().add("big-menu-button");

        saveTransformation.setOnAction(actionEvent -> {
            if (realPart.getText().isEmpty() || imaginaryPart.getText().isEmpty()){
                errorLabel.setText("Please fill in all fields");
                return;
            }
            try{
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
                realPart.clear();
                imaginaryPart.clear();
                errorLabel.setText("");
            } catch (NumberFormatException e){
                errorLabel.setText("Please enter a valid number");
            }
        });

        VBox saveAsFileBox = new VBox();
        fileText = new Text("No path selected");
        saveAsFile = new Button("Save as file");
        saveAsFile.getStyleClass().add("big-menu-button");
        saveAsFileBox.getChildren().addAll(saveAsFile, fileText);
        saveAsFileBox.setPadding(new Insets(20, 10, 10, 20));
        saveAsFile();

        VBox newTransformationMenu = new VBox(
                realLabel, realPartBox,
                imaginaryLabel, imaginaryPartBox, errorLabel,
                saveTransformation, saveAsFileBox);
        newTransformationMenu.setSpacing(5);
        newTransformationMenu.setPadding(new Insets(0, 0, 0, 10));
        VBox.setMargin(imaginaryPart, new Insets(5,0,15, 0));
        VBox.setMargin(realPart, new Insets(5,0,15, 0));
        VBox.setMargin(saveTransformation, new Insets(5,0,15, 0));
        getRoot().setLeft(newTransformationMenu);
    }

    public void saveAsFile(){
        saveAsFile.setOnAction(actionEvent -> {
            try {
                if (getChaosGameDescription() == null) {
                    throw new IllegalArgumentException("No transformation to save");
                }else {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save Transformation File");
                    File file = fileChooser.showSaveDialog(null);

                    if (file != null) {
                        fileText.setText(file.getAbsolutePath());
                        System.out.println("Path chosen");

                        chaosGameFileHandler.writeToFile(getChaosGameDescription(), file.getAbsolutePath());
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
    }

    public void showFractalOnActionCreate(){
        getShowFractal().setOnAction(actionEvent -> {
            getChaosGameDescription().setMinCoords(getMinCoords());
            getChaosGameDescription().setMaxCoords(getMaxCoords());
            try {
                setChaosGame(new ChaosGame(getChaosGameDescription(), getWidth(), getHeight()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            displayFractal();
            newTransformations.clear();
        });
    }

    public List<Transform2D> getNewTransformations() {
        return newTransformations;
    }


}
