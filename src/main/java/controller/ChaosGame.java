package controller;

import controller.ChaosCanvas;
import model.AffineTransform2D;
import model.Matrix2x2;
import model.Transform2D;
import model.Vector2D;

import java.util.List;
import java.util.Random;

public class ChaosGame {
    private ChaosCanvas canvas;
    private ChaosGameDescription description;
    private Vector2D currentPoint;
    private Random random;
    private ChaosCanvas chaosCanvas;

    public ChaosGame(ChaosGameDescription chaosGameDescription, int width, int height){
        this.description = chaosGameDescription;
        this.canvas = new ChaosCanvas(width, height, chaosGameDescription.getMinCoords(), chaosGameDescription.getMaxCoords());
        this.currentPoint = new Vector2D(0, 0);
        this.random = new Random();
    }
    public ChaosCanvas getCanvas(){
        return canvas;
    }
    public void runSteps(int steps){
        Vector2D p1 = new Vector2D(canvas.getMaxCoords().getX0()/2, canvas.getMaxCoords().getX1()-1);
        Vector2D p2 = new Vector2D(canvas.getMinCoords().getX0(), canvas.getMinCoords().getX1());
        Vector2D p3 = new Vector2D(canvas.getMaxCoords().getX0()-1, canvas.getMinCoords().getX1());
        currentPoint = new Vector2D(p2.getX0(), p2.getX1());

        Vector2D drawPoint = currentPoint;
        Vector2D chosenPoint = p1;

        ChaosGameDescription description = this.description;
        System.out.println(description.getTransforms().get(0).toString());
        List<Transform2D> transform2DList= description.getTransforms();

        /*boolean areFirstTwentyJuliaTransforms(ChaosGameDescription description) {
            List<Transform2D> transforms = description.getTransforms();
            int count = 0;

            for (int i = 0; i < Math.min(20, transforms.size()); i++) {
                if (transforms.get(i) instanceof model.JuliaTransform) {
                    count++;
                }
            }

            return count == 20;
        }*/

        double M = description.getMaxCoords().getX0();

        double N = description.getMaxCoords().getX1();

        double Ax1 = (N-1)/(description.getMinCoords().getX1()-description.getMaxCoords().getX1());

        double Ax2 = (M-1)/(description.getMaxCoords().getX0()-description.getMinCoords().getX0());

        double b1 = ((N-1)*description.getMaxCoords().getX1()) / (description.getMaxCoords().getX1()-description.getMinCoords().getX1());

        double b2 = ((M-1)*description.getMinCoords().getX0())/(description.getMinCoords().getX0()-description.getMaxCoords().getX0());

        Matrix2x2 matrixA = new Matrix2x2(0, Ax1, Ax2, 0);
        Vector2D b = new Vector2D(b1, b2);


        AffineTransform2D transform = new AffineTransform2D(matrixA, b);

        canvas.putPixel(transform.Transform(p1));
        //System.out.println("P2: "+transform.Transform(p2).getX0() + " " + transform.Transform(p2).getX1());
        canvas.putPixel(transform.Transform(p2));
        //System.out.println("P3: "+transform.Transform(p3).getX0() + " " + transform.Transform(p3).getX1());
        canvas.putPixel(transform.Transform(p3));
        //System.out.println("Start punkt: " + transform.Transform(currentPoint).getX0() + " " + transform.Transform(currentPoint).getX1());
        canvas.putPixel(transform.Transform(currentPoint));

        for(int i = 0; i < steps; i++){
            int dice = random.nextInt(3)+1;

            //System.out.println("Round: " + (i + 1));

            if(dice == 1){
                //System.out.println("Threw: 1");
                chosenPoint = p1;
            }
            else if(dice == 2){
                chosenPoint = p2;
                //System.out.println("Threw: 2");
            }
            else if(dice == 3){
                chosenPoint = p3;
                //System.out.println("Threw: 3");
            }
            currentPoint = new Vector2D((chosenPoint.getX0() + currentPoint.getX0()) / 2, (chosenPoint.getX1() + currentPoint.getX1()) / 2);
            //System.out.println("i:" + currentPoint.getX0());
            //System.out.println("j: " + currentPoint.getX1());
            drawPoint = transform.Transform(currentPoint);
            //System.out.println("Drawpoint X0: " + drawPoint.getX0());
            //System.out.println("Drawpoint X1: " + drawPoint.getX1());
            canvas.putPixel(drawPoint);
            //System.out.println("\n");
        }
    }
}
