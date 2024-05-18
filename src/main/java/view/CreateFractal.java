package view;

import controller.ChaosGameDescription;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.AffineTransform2D;
import model.Matrix2x2;
import model.Transform2D;
import model.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class CreateFractal extends Fractal{
    private final List<Transform2D> newTransformations = new ArrayList<>();

    public void setNewTransformationMenu(){
        setMenu();
        buttonsOnAction();
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

        Button saveAsFile = new Button("Save as file");
        //noe her

        VBox newTransformationMenu = new VBox(
                vectorLabel, vectorX0, vectorX1,
                matrixLabel, matrix00, matrix01, matrix10, matrix11,
                saveTransformation, saveAsFile);
        newTransformationMenu.setSpacing(5);
        newTransformationMenu.setPadding(new Insets(0, 0, 0, 10));
        VBox.setMargin(vectorX1, new Insets(5,0,15, 0));
        VBox.setMargin(matrix11, new Insets(5,0,15,0));
        VBox.setMargin(saveTransformation, new Insets(5,0,15, 0));
        getRoot().setLeft(newTransformationMenu);
    }

    public List<Transform2D> getNewTransformations() {
        return newTransformations;
    }
}
