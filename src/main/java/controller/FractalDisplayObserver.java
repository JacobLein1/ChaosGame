package controller;

import javafx.scene.image.ImageView;
import model.ChaosGame;

/**
 * This class observes the fractals displayed in the GUI.
 */
public class FractalDisplayObserver implements ChaosGameObserver{
    private final InitializeChaosGame initializeChaosGame;
    private ImageView fractalImageView = new ImageView();

    /**
     * Instantiates a new Fractal display observer.
     *
     * @param chaosGame the chaos game
     * @param steps     the steps
     * @param width     the width
     * @param height    the height
     */
    public FractalDisplayObserver(ChaosGame chaosGame, int steps, int width, int height){
        this.initializeChaosGame = new InitializeChaosGame(chaosGame, steps, width, height);
    }
    @Override
    public void updateGame() {
        fractalImageView = initializeChaosGame.createFractalDisplay();
    }

    /**
     * Gets the fractal image as an Imageview.
     *
     * @return the fractal image
     */
    public ImageView getFractalImageView() {
        return fractalImageView;
    }
}
