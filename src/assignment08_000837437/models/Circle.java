package assignment08_000837437.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Implementation of a shape that describes a circle and its radius
 * This is the class header.
 *
 * @date April 3, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class Circle extends GeometricObject{
    /**The radius of a circle*/
    private double radius;

    /**
     * Constructor to determine the X location, Y location, the radius and the color of a circle
     *
     * @param x the x location of a circle
     * @param y the y location of a circle
     * @param radius the y location of a circle
     * @param fillColor the color of a circle
     */
    public Circle(double x, double y, double radius, Color fillColor) {
        super(x, y, fillColor);
        if (radius<=0 || radius>100)    // throw an exception if the radius of this circle are not greater than 0 or too big
            throw new IllegalArgumentException("Size "+(radius<=0 ?"must be greater than 0": "is too big")+". ");
        this.radius = radius;
    }

    /**
     * The draw method of a circle that draws it on a GraphicsContext object
     * */
    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(getFillColor());
        gc.fillOval(getX()-radius, getY()-radius,radius*2,radius*2);    //draw a circle
    }
}
