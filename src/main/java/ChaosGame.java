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
        Vector2D p1 = new Vector2D(50, 1);
        Vector2D p2 = new Vector2D(1, 99);
        Vector2D p3 = new Vector2D(99, 99);
        currentPoint = new Vector2D(1,99);
        canvas.putPixel(p1);
        canvas.putPixel(p2);
        canvas.putPixel(p3);
        canvas.putPixel(currentPoint);


        for(int i = 0; i < steps; i++){
            int result = dice.nextInt(1, 3);

            double Ax1 = (currentPoint.getX1()-1)/(canvas.getMinCoords().getX1()-canvas.getMaxCoords().getX1());

            double Ax2 = (currentPoint.getX0()-1)/(canvas.getMaxCoords().getX0()-canvas.getMinCoords().getX0());

            double b1 = ((currentPoint.getX1()-1)*canvas.getMaxCoords().getX1()) / (canvas.getMaxCoords().getX1()-canvas.getMinCoords().getX1());

            double b2 = ((currentPoint.getX0()-1)*canvas.getMinCoords().getX0())/(canvas.getMinCoords().getX0()-canvas.getMaxCoords().getX0());

            AffineTransform2D transform = new AffineTransform2D(new Matrix2x2(0, Ax1, Ax2, 0), new Vector2D(b1, b2));
            if(result == 1){
                System.out.println("Threw: 1");
                currentPoint = transform.Transform(p1);
                System.out.println("x0:" + currentPoint.getX0());
                System.out.println("x1 " + currentPoint.getX1());
            }
            else if(result == 2){
                System.out.println("Threw: 2");
                currentPoint = transform.Transform(p2);
                System.out.println("x0:" + currentPoint.getX0());
                System.out.println("x1 " + currentPoint.getX1());
            }
            else if(result == 3){
                System.out.println("Threw: 3");
                currentPoint = transform.Transform(p3);
                System.out.println("x0:" + currentPoint.getX0());
                System.out.println("x1 " + currentPoint.getX1());
            }
            canvas.putPixel(currentPoint);
        }
    }
}
