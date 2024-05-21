package controller;

import model.Transform2D;
import model.Vector2D;

import java.util.List;

/**
 * The type Chaos game description.
 */
public class ChaosGameDescription {
    private Vector2D minCoords;
    private Vector2D maxCoords;
    private List<Transform2D> transforms;

    /**
     * Instantiates a new Chaos game description.
     *
     * @param minCoords  the min coords
     * @param maxCoords  the max coords
     * @param transforms the transforms
     */
    public ChaosGameDescription(Vector2D minCoords, Vector2D maxCoords, List<Transform2D> transforms){
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        this.transforms = transforms;
    }

    public Vector2D getMinCoords() {
        return minCoords;
    }

    public void setMinCoords(Vector2D minCoords) { this.minCoords = minCoords; }

    public Vector2D getMaxCoords() {
        return maxCoords;
    }

    public void setMaxCoords(Vector2D maxCoords) { this.maxCoords = maxCoords; }

    public List<Transform2D> getTransforms() {
        return transforms;
    }
}
