package assignment08_000837437.models;

import assignment08_000837437.test.PainApp;
import javafx.scene.paint.Color;

/**
 * Implementation of a Shape has its Color and X location and Y location
 * This is the class header.
 *
 * @date April 3, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public abstract class GeometricObject implements Drawable{
    /**The X location of a shape*/
    private double x;
    /**The Y location of a shape*/
    private double y;
    /**The Color of a shape*/
    private Color fillColor;


    /**
     * Constructor to determine the X location, Y location and the Color of a shape
     *
     * @param x the x location of a shape
     * @param y the y location of a shape
     * @param fillColor the color of a shape
     */
    public GeometricObject(double x, double y, Color fillColor) {
        if (x<0 || y<0)     // throw an exception if the locations of this shape are not greater than 0 or too big
            throw new IllegalArgumentException(((x<0 && y<0) ? "X("+x+") and Y("+y+")" : (x<0 ? "X("+x+")" : "Y("+y+")"))
                     +" location"+((x<0 && y<0)?"s":"")+" must be greater than 0. ");
        if (x> PainApp.WIDTH_OF_WINDOW || y>PainApp.HEIGHT_OF_WINDOW-PainApp.HEIGHT_OF_NORTH-PainApp.HEIGHT_OF_SOUTH)
            throw new IllegalArgumentException((x>PainApp.WIDTH_OF_WINDOW ? "X("+x+")" : "Y("+y+")")+" Location is too big. ");
        if (fillColor.getRed()<0 || fillColor.getRed()>255)     // throw an exception if the red variable of the color is wrong
            throw new IllegalArgumentException(fillColor.getRed()+". ");
        if (fillColor.getGreen()<0 || fillColor.getGreen()>255) // throw an exception if the green variable of the color is wrong
            throw new IllegalArgumentException(fillColor.getGreen()+". ");
        if (fillColor.getBlue()<0 || fillColor.getBlue()>255)   // throw an exception if the blue variable of the color is wrong
            throw new IllegalArgumentException(fillColor.getBlue()+". ");
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
    }

    /**
     * The X location of a shape can be easily retrieved by anyone.
     * @return an integer number describes a X location
     * */
    public double getX() {
        return x;
    }

    /**
     * The Y location of a shape can be easily retrieved by anyone.
     * @return an integer number describes a Y location
     * */
    public double getY() {
        return y;
    }

    /**
     * The color of a shape can be easily retrieved by anyone.
     * @return a Color describes a Color of the shape
     * */
    public Color getFillColor() {
        return fillColor;
    }
}
