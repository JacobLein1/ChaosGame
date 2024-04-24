package controller;

import model.Vector2D;

import java.util.Random;

public class ChaosGame implements ChaosGameObserver{
    private ChaosCanvas canvas;
    private ChaosGameDescription description;
    private Vector2D currentPoint;
    private Random random;


    public ChaosGame(ChaosGameDescription chaosGameDescription, int width, int height){
        this.description = chaosGameDescription;
        this.canvas = new ChaosCanvas(width, height, chaosGameDescription.getMinCoords(), chaosGameDescription.getMaxCoords());
        this.currentPoint = new Vector2D(chaosGameDescription.getMinCoords().getX0(), chaosGameDescription.getMinCoords().getX1());
        this.random = new Random();
    }
    public ChaosCanvas getCanvas(){
        return canvas;
    }

    public void runSteps(int steps){
        for (int j = 0; j < steps; j++) {
            int randomInt = random.nextInt(description.getTransforms().size());
            System.out.println("Random int: " + randomInt);
            System.out.println("Current point before transform:" + currentPoint.getX0() + " " + currentPoint.getX1());
            currentPoint = description.getTransforms().get(randomInt).Transform(currentPoint);
            System.out.println("Current point after transform:" + currentPoint.getX0() + " " + currentPoint.getX1());
            canvas.putPixel(currentPoint);
        }
    }

    @Override
    public void updateGame() {

    }

}
