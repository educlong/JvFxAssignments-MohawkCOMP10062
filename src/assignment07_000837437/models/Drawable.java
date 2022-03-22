package assignment07_000837437.models;

import javafx.scene.canvas.GraphicsContext;
/**
 * Description methods of drawing with x location
 * This is the interface header.
 *
 * @date March 26, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public interface Drawable {
    /**The draw method draws a building on a GraphicsContext object*/
    public void draw(GraphicsContext gc);
    /**The X location of a building object in a GraphicsContext
     * @return the X location of a building object
     * */
    public double getXPosition();
}
