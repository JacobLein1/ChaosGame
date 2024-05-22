package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This page displays the home page
 */
public class HomePage extends Application{
    private Stage homeStage;
    private final BorderPane root = new BorderPane();
    private ComboBox<String> fractalType;

    /**
     * Instantiates a new Home page.
     */
    public HomePage(){
        setMenu();
    }

    @Override
    public void start(Stage homeStage) {
        this.homeStage = homeStage;

        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add("/css/GameStyles.css");

        // Set up the stage
        homeStage.setTitle("Chaos Game");
        homeStage.setScene(scene);
        homeStage.show();
    }

    /**
     * Set menu.
     */
    public void setMenu(){
        //all nodes
        String[] fractals = {"Affine2D", "Barnsley", "Julia", "Create new Affine transformation", "Create new Barnsley transformation", "Create new Julia transformation", "Upload from files"};
        fractalType = new ComboBox<>(FXCollections.observableArrayList(fractals));
        Label header = new Label("Chaos Game");
        Button chooseFractal = new Button("Choose fractal");

        //action for "Choose fractal" button
        chooseFractal.setOnAction(actionEvent -> {
            try {
                chooseFractalOnAction();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //set style
        fractalType.setPrefHeight(30);
        fractalType.getStyleClass().add("menu-combobox");
        chooseFractal.getStyleClass().add("big-home-button");
        header.getStyleClass().add("homepage-header");

        HBox menu = new HBox(fractalType, chooseFractal);
        menu.setSpacing(30);
        menu.setAlignment(Pos.CENTER);
        HBox.setMargin(fractalType, new Insets(20, 10, 10, 20));
        HBox.setMargin(chooseFractal, new Insets(20, 10, 10, 20));

        VBox center = new VBox(header, menu);
        center.setAlignment(Pos.CENTER);

        root.setCenter(center); //sets the menu to the center part of the BorderPane


        root.getStyleClass().add("background");
    }

    /**
     * Action for the "Choose fractal" button
     *
     * @throws Exception the exception
     */
    public void chooseFractalOnAction() throws Exception {
        if(fractalType.getValue().equals("Julia")){
            JuliaPage juliaPage = new JuliaPage();
            juliaPage.start(homeStage); //switch to Julia page
        }
        if(fractalType.getValue().equals("Affine2D")){
            Affine2DPage affine2DPage = new Affine2DPage();
            affine2DPage.start(homeStage); //switch to Affine Sierpinski triangle page
        }
        if(fractalType.getValue().equals("Barnsley")){
            BarnsleyPage barnsleyPage = new BarnsleyPage();
            barnsleyPage.start(homeStage); //switch to Barnsley fern page
        }
        if(fractalType.getValue().equals("Create new Affine transformation")){
            CreateAffinePage createAffinePage = new CreateAffinePage();
            createAffinePage.start(homeStage); //switch to create new Affine fractal page
        }
        if(fractalType.getValue().equals("Create new Barnsley transformation")){
            CreateBarnsleyPage createBarnsleyPage = new CreateBarnsleyPage();
            createBarnsleyPage.start(homeStage); //switch to create new Barnsley fractal page
        }
        if (fractalType.getValue().equals("Upload from files")) {
            UploadFilePage uploadPage = new UploadFilePage();
            uploadPage.start(homeStage); //switch to upload values from file page
        }
        if(fractalType.getValue().equals("Create new Julia transformation")){
            CreateJuliaPage createJuliaPage = new CreateJuliaPage();
            createJuliaPage.start(homeStage); //switch to create new Julia fractal page
        }
    }

    /**
     * Launches the application
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
       HomePage.launch(args);
    }
}
