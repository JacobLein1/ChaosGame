package view;

import controller.ChaosGame;
import controller.ChaosGameDescription;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.AffineTransform2D;
import model.Matrix2x2;
import model.Transform2D;
import model.Vector2D;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends Application{
    private Stage homeStage;
    private final BorderPane root = new BorderPane();
    private ComboBox<String> fractalType;

    public HomePage(){
        HBox bottom = new HBox();
        bottom.setPrefSize(50, 50);
        root.setBottom(bottom);
        setMenu();
    }

    @Override
    public void start(Stage homeStage) {
        this.homeStage = homeStage;

        Scene scene = new Scene(root, 1000, 800);

        // Set up the stage
        homeStage.setTitle("Chaos Game");
        homeStage.setScene(scene);
        homeStage.show();
    }

    public void setMenu(){
        Label header = new Label("Home");
        String[] fractals = {"Affine2D", "Barnsley", "Julia", "Create new Affine transformation", "Create new Barnsley transformation", "Upload from files"};
        fractalType = new ComboBox<>(FXCollections.observableArrayList(fractals));

        Button chooseFractal = new Button("Choose fractal");
        chooseFractal.setOnAction(actionEvent -> {
            try {
                chooseFractalOnAction();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        HBox menu = new HBox(header, fractalType, chooseFractal);
        menu.setSpacing(50);
        HBox.setMargin(header, new Insets(20, 20, 10, 10));
        HBox.setMargin(fractalType, new Insets(20, 10, 10, 20));
        HBox.setMargin(chooseFractal, new Insets(20, 10, 10, 20));
        root.setTop(menu);
    }

    public void chooseFractalOnAction() throws Exception {
        if(fractalType.getValue().equals("Julia")){
            JuliaPage juliaPage = new JuliaPage();
            juliaPage.start(homeStage);
        }
        if(fractalType.getValue().equals("Affine2D")){
            Affine2DPage affine2DPage = new Affine2DPage();
            affine2DPage.start(homeStage);
        }
        if(fractalType.getValue().equals("Barnsley")){
            BarnsleyPage barnsleyPage = new BarnsleyPage();
            barnsleyPage.start(homeStage);
        }
        if(fractalType.getValue().equals("Create new Affine transformation")){
            CreateAffinePage createAffinePage = new CreateAffinePage();
            createAffinePage.start(homeStage);
        }
        if(fractalType.getValue().equals("Create new Barnsley transformation")){
            CreateBarnsleyPage createBarnsleyPage = new CreateBarnsleyPage();
            createBarnsleyPage.start(homeStage);
        }
    }

    public static void main(String[] args) {
       HomePage.launch(args);
    }

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
