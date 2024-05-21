package controller;

import javafx.scene.image.ImageView;
import view.InitializeChaosGame;

public class FractalDisplayObserver implements ChaosGameObserver{
    private final InitializeChaosGame initializeChaosGame;
    private ImageView fractalImageView = new ImageView();
    public FractalDisplayObserver(ChaosGame chaosGame, int steps, int width, int height){
        this.initializeChaosGame = new InitializeChaosGame(chaosGame, steps, width, height);
    }
    @Override
    public void updateGame() {
        fractalImageView = initializeChaosGame.createFractalDisplay();
    }
    public ImageView getFractalImageView() {
        return fractalImageView;
    }
}
