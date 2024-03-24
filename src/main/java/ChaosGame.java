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
        Random dice = new Random();
        Vector2D p1 = new Vector2D(50, 0);
        Vector2D p2 = new Vector2D(0, 99);
        Vector2D p3 = new Vector2D(99, 99);
        currentPoint = new Vector2D(0,99);
        canvas.putPixel(p1);
        canvas.putPixel(p2);
        canvas.putPixel(p3);
        canvas.putPixel(currentPoint);


        for(int i = 0; i < steps; i++){
            int result = dice.nextInt(1, 3);
            AffineTransform2D transform = new AffineTransform2D(new Matrix2x2(0, currentPoint.getX0()-1/(canvas.getMinCoords().getX1()-canvas.getMaxCoords().getX1()), currentPoint.getX1()-1/(canvas.getMaxCoords().getX0()-canvas.getMinCoords().getX0()), 0), new Vector2D((
                    ((currentPoint.getX0())-1)*canvas.getMaxCoords().getX1())/(canvas.getMaxCoords().getX1()-canvas.getMinCoords().getX1())
                    ,((currentPoint.getX1()-1)*canvas.getMinCoords().getX0())/(canvas.getMinCoords().getX0()-canvas.getMaxCoords().getX0())));
            if(result == 1){
                currentPoint = transform.Transform(p1);
            }
            else if(result == 2){
                currentPoint = transform.Transform(p2);
            }
            else if(result == 3){
                currentPoint = transform.Transform(p3);
            }
            canvas.putPixel(currentPoint);
        }
    }
}
