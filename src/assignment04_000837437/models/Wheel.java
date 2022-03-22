package assignment04_000837437.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * Implementation of a wheels has a set of faces in a array(a string array. e.g: "Cherry", "Banana", "Grapes", etc.)
 * It also has a color that it uses to display itself and it has the number of faces
 * It can spin itself, and it can report the number of faces it has
 * It can report the index (0 or more) of the current face that is showing.
 * This is the class header.
 *
 * @date March 1st, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class Wheel {
    /**the faces of a wheel stored in an array **/
    private String[]faces;
    /**the color of faces in a wheel*/
    private Color color;
    /**the number of faces of a wheel*/
    private int numFaces;

    /**
     * Constructor to determine the number of faces, the color and the text of each face on the wheel.
     * They is set when Wheel object is created and cannot be changed (meaning they do not have setter methods)
     *
     * @param numFaces the number of faces of a wheel.
     * @param faces the faces of a wheel stored in an array
     * @param color the color of faces in a wheel
     */
    public Wheel(int numFaces, String[] faces, Color color) {
        this.numFaces = numFaces;
        this.color=color;
        this.faces=new String[numFaces];
        for (int count=0; count<numFaces;count++)
            this.faces[count] = faces[count];
    }

    public int getNumFaces() {
        return numFaces;
    }

    /**
     * The indexFaceShowing method generates a random number between 1 and numFaces
     * @return an integer number as the index (0 or more) of the current face that is showing
     */
    public int indexSpinWheel(){
        return (int)(Math.random()*this.numFaces);
    }

    /**
     * The spinWheel method descripts that a wheel can spin itself.
     * @return a string as a face of a wheel from indexFaceShowing method after spinning
     */
    public String spinWheel(){
        return this.faces[indexSpinWheel()];
    }

    /**
     * The getElementFace method return the text of a face selected from index
     * @param index the index of the face selected
     * @return a string as a face selected of a wheel from index param
     * */
    public String getElementFace(int index) {
        return faces[index];
    }

    /**
     * The draw method can draw the text of a face on a GraphicsContextwhen it is given an x and y location.
     * @param gc class GraphicsContext is used to issue draw calls to a Canvas using a buffer
     * @param locationX draw this face in location X
     * @param locationY draw this face in location Y
     * */
    public void draw(GraphicsContext gc,double locationX, double locationY){
        gc.setStroke(this.color);
        gc.strokeText(spinWheel(),locationX,locationY);
    }

    /**
     * The toString method prints a text of a face of a wheel after spinning
     * @return a string text of a face of a wheel after spinning
     */
    @Override
    public String toString() {
        return spinWheel();
    }
}
