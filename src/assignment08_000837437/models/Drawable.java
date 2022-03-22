package assignment08_000837437.models;

import javafx.scene.canvas.GraphicsContext;
/**
 * Description methods of drawing
 * This is the interface header.
 *
 * @date April 3, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */

public interface Drawable {
    /**
     * The draw method draws a shape on a GraphicsContext object
     * */
    public void draw(GraphicsContext gc);
}
