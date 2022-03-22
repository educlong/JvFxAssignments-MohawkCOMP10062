package assignment08_000837437.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Implementation of a shape that describes a square and its size
 * This is the class header.
 *
 * @date April 3, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class Square extends GeometricObject{
    /**The size of a square*/
    private double size;

    /**
     * Constructor to determine the X location, Y location, the size and the color of a square
     *
     * @param x the x location of a square
     * @param y the y location of a square
     * @param size the y location of a square
     * @param fillColor the color of a square
     */
    public Square(double x, double y,  double size, Color fillColor) {
        super(x, y, fillColor);
        if (size<=0 || size>100)    // throw an exception if the size of this square are not greater than 0 or too big
            throw new IllegalArgumentException("Size "+(size<=0 ?"must be greater than 0": "is too big")+". ");
        this.size = size;
    }

    /**
     * The draw method of a square that draws it on a GraphicsContext object
     * */
    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(getFillColor());
        gc.fillRect(getX()-size,getY()-size,size*2,size*2); //draw a square
    }
}
