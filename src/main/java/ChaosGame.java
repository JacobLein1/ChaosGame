import java.util.Random;

public class ChaosGame {
    private ChaosCanvas canvas;
    private ChaosGameDescription description;
    private Vector2D currentPoint;
    private Random random;

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
        for(int i = 0; i < steps; i++){
            Transform2D transform = description.getTransforms().get(random.nextInt(description.getTransforms().size()));
            currentPoint = transform.Transform(currentPoint);
            canvas.putPixel(currentPoint);
        }
    }
}
