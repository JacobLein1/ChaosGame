package model;

import controller.ChaosGameDescription;
import controller.ChaosGameObservable;

import java.util.Random;

/**
 * The type Chaos game.
 */
public class ChaosGame extends ChaosGameObservable {
    private ChaosCanvas canvas;
    private ChaosGameDescription description;
    private Vector2D currentPoint;
    private Random random;


    /**
     * Instantiates a new Chaos game.
     *
     * @param chaosGameDescription the chaos game description
     * @param width                the width
     * @param height               the height
     */
    public ChaosGame(ChaosGameDescription chaosGameDescription, int width, int height){
        this.description = chaosGameDescription;
        this.canvas = new ChaosCanvas(width, height, chaosGameDescription.getMinCoords(), chaosGameDescription.getMaxCoords());
        this.currentPoint = new Vector2D(0,0);
        this.random = new Random();
    }

    public ChaosCanvas getCanvas(){
        return canvas;
    }

    /**
     * Runs the chaos game for a given number of steps,
     * where each step consists of randomly selecting a random transformation of
     * the chaos game descriptio and applying it to the current point.
     * The resulting point is then drawn on the canvas.
     *
     * @param steps the steps
     */
    public void runSteps(int steps){
        for (int j = 0; j < steps; j++) {
            int randomInt = random.nextInt(description.getTransforms().size());
            currentPoint = description.getTransforms().get(randomInt).Transform(currentPoint);
            canvas.putPixel(currentPoint);
        }
    }
}
