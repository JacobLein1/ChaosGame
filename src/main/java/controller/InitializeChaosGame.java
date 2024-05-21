package controller;

import model.ChaosGame;
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
    public ImageView createFractalDisplayGreyScale(){
        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();

        /*for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int value = chaosGame.getCanvas().getCanvasArray()[i][j] == 1 ? 0xFF000000 : 0xFFD3D3D3;
                pixelWriter.setArgb(i, j, value);
            }
        }*/
        int[][] canvasArray = chaosGame.getCanvas().getCanvasArray();
        int maxHits = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (canvasArray[i][j] > maxHits) {
                    maxHits = canvasArray[i][j];
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int hits = canvasArray[i][j];
                int grayScaleValue = (int) (255 * (1 - (double) hits / maxHits));
                int value = (0xFF << 24) | (grayScaleValue << 16) | (grayScaleValue << 8) | grayScaleValue;
                if (hits == 0){
                    pixelWriter.setArgb(i,j, 0xFFD3D3D3);
                }
                pixelWriter.setArgb(i, j, value);
            }
        }
        /*
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int hits = canvasArray[i][j];
                if (hits == 0) {
                    pixelWriter.setArgb(i, j, 0xFFD3D3D3);
                } else {
                    //Normalise to value between 0 and 255
                    double scale = (double) hits / maxHits;
                    int red = (int) (211 * (1 - scale));
                    int green = (int) (211 * (1 - scale));
                    int blue = (int) (211 * (1 - scale) + 255 * scale);

                    int value = (0xFF << 24) | (red << 16) | (green << 8) | blue;
                    pixelWriter.setArgb(i, j, value);
                }
            }
        }*/

        ImageView view = new ImageView(image);
        view.setRotate(90);
        return view;
    }
}
