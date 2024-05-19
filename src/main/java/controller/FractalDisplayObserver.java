package controller;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import view.InitializeChaosGame;

public class FractalDisplayObserver implements ChaosGameObserver{
    private final InitializeChaosGame initializeChaosGame;
    private final ImageView fractalImageView;
    public FractalDisplayObserver(InitializeChaosGame initializeChaosGame, ImageView imageView){
        this.initializeChaosGame = initializeChaosGame;
        this.fractalImageView = imageView;
    }
    @Override
    public void updateGame() {
        Platform.runLater(() -> {
            fractalImageView.setImage(initializeChaosGame.createFractalDisplay().getImage());
        });
    }
    public ImageView getFractalImageView() {
        return fractalImageView;
    }
}
