package controller;

import model.AffineTransform2D;
import model.JuliaTransform;
import model.Transform2D;
import model.Vector2D;

import java.util.List;

/**
 * The type Chaos game description.
 */
public class ChaosGameDescription {
    private final Vector2D minCoords;
    private final Vector2D maxCoords;
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

    public Vector2D getMaxCoords() {
        return maxCoords;
    }

    public List<Transform2D> getTransforms() {
        return transforms;
    }
}
