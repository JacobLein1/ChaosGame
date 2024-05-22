package controller;

import model.Transform2D;
import model.Vector2D;

import java.util.List;

/**
 * This class represents a fractal and contains the coordinates for the display and the fractals transformations.
 */
public class ChaosGameDescription {
    private Vector2D minCoords;
    private Vector2D maxCoords;
    private List<Transform2D> transforms;

    /**
     * Instantiates a new Chaos game description.
     *
     * @param minCoords  the minimum coordinates
     * @param maxCoords  the maximum coordinates
     * @param transforms the transformations
     */
    public ChaosGameDescription(Vector2D minCoords, Vector2D maxCoords, List<Transform2D> transforms){
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        this.transforms = transforms;
    }

    /**
     * Gets the minimum coordinates.
     *
     * @return the minimum coordinates as a Vector2D
     */
    public Vector2D getMinCoords() {
        return minCoords;
    }

    /**
     * Sets the minimum coordinates.
     *
     * @param minCoords the new coordinates
     */
    public void setMinCoords(Vector2D minCoords) { this.minCoords = minCoords; }

    /**
     * Gets the maximum coordinates.
     *
     * @return the maximum coordinates as a Vector2D
     */
    public Vector2D getMaxCoords() {
        return maxCoords;
    }

    /**
     * Sets the maximum coordinates.
     *
     * @param maxCoords the new coordinates
     */
    public void setMaxCoords(Vector2D maxCoords) { this.maxCoords = maxCoords; }

    /**
     * Gets the transformations.
     *
     * @return the transformations as a List
     */
    public List<Transform2D> getTransforms() {
        return transforms;
    }
}
