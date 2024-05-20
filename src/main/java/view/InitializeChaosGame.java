package view;

import controller.ChaosGame;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class InitializeChaosGame {

    private final ChaosGame chaosGame;
    private final int width;
    private final int height;

    public InitializeChaosGame(ChaosGame chaosGame, int steps, int width, int height) {
        this.chaosGame = chaosGame;
        this.width = width;
        this.height = height;

        try{
            if(steps < 100000000){
                chaosGame.runSteps(steps);
            }else{
                throw new RuntimeException("The number is too high");
            }
        } catch (NumberFormatException n){
            throw new IllegalArgumentException("The input must be a number");
        }
    }

    public ImageView createFractalDisplay(){
        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int value = chaosGame.getCanvas().getCanvasArray()[i][j] == 1 ? 0xFF000000 : 0xFFD3D3D3;
                pixelWriter.setArgb(i, j, value);
            }
        }

        ImageView view = new ImageView(image);
        view.setRotate(90);
        return view;
    }
}
