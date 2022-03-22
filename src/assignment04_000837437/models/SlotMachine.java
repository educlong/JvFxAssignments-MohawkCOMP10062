package assignment04_000837437.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * Implementation of a Slot Machine has a set of Wheel objects in a array.
 * Each Wheel object has a different color, but each has the same set of faces.
 * A Slot Machine can spin its wheels, and can report the current payout.
 * It has a histogram method to spin the wheels a million times.
 * This is the class header.
 *
 * @date March 1st, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class SlotMachine {
    /**A Slot Machine can spin a OCCURRENCES times to make a histogram*/
    public static final int OCCURRENCES=1000000;
    /**The wheels of a SlotMachine stored in an array **/
    private Wheel[] wheels;
    /**The colors of wheels in a Slot Machine stored in an array **/
    private Color[] colors;
    /**The number of faces of a wheel*/
    private int numFaces;
    /**The text of selected faces of wheels in a Slot Machine after spinning stored in an array **/
    private String[] faceSelected;

    /**
     * A default constructor is a constructor which can be called with no arguments
     * */
    public SlotMachine() {
    }

    /**
     * Constructor to determine the number of Wheel objects, the number of faces of a wheel
     * as well as their color, the text of each face of a wheel on the wheel.
     * They is set when a Slot Machine object is created and stored them in an array.
     *
     * @param numWheels the number of Wheel objects in a Slot Machine
     * @param colors the color of faces in wheels stored in an array
     * @param numFaces the number of faces of a wheel.
     * @param faces the faces of a wheel stored in an array
     */
    public SlotMachine(int numWheels,Color[] colors, int numFaces, String[] faces) {
        this.numFaces=numFaces;
        this.colors=new Color[numWheels];
        this.wheels=new Wheel[numWheels];
        for (int count=0;count<numWheels;count++){
            this.colors[count]=colors[count];
            this.wheels[count]=new Wheel(numFaces,faces,colors[count]);
        }
        this.faceSelected=new String[numWheels];
    }


    /**
     * The spinWheelsInSlotMachine method descripts that a Slot Machine can spin its wheels.
     * @return an integer array as the indexes of selected faces of a Slot Machine after spinning.
     */
    private int[] spinWheelsInSlotMachine(){
        int[] indexFaceSelected = new int[wheels.length];
        for (int count=0; count<wheels.length; count++)                     //spin wheels in a array of wheels of a Slot Machine
            indexFaceSelected[count] = this.wheels[count].indexSpinWheel(); //and return indexes of selected faces,
        return indexFaceSelected;                                           //then stored them and return an indexFaceSelected array
    }

    /**
     * The payout method report the current payout. If none of the wheels are showing matching faces, the payout is 0.
     * If at most two of them match, the payout is 1, if at most three match the payout is 2, and so on.
     * The payout method calls spinWheelsInSlotMachine method and uses payoutAlgorithm method below to find out the payout number
     * @retrun a string including the current state of each wheel, and the current payout.
     * */
    private String payout(){
        String faces="";
        int[] indexFace = spinWheelsInSlotMachine();        //Stored the indexes of selected faces after spinning into a countFace array
        for (int count=0; count<wheels.length; count++) {   //Stored the texts of selected faces after spinning into a faceSelected array
            this.faceSelected[count]=this.wheels[count].getElementFace(indexFace[count]);
            faces += this.faceSelected[count] + (count == wheels.length - 1 ? "]" : ", ");  //and print the current state of each wheel
        }
        return faces+"\n\t\t => Payout="+payoutAlgorithm(indexFace);    //print the current state of each wheel and the current payout.
    }

    /**
     * This method is an algorithm which finds out the payout of a Slot Machine after spinning.
     * @param indexFace an integer array as the indexes of selected faces of a Slot Machine after spinning.
     * @return the payout number which is descriptive from the header of payout method.
     * */
    private int payoutAlgorithm(int[]indexFace){
        int[] countTmp =new int[numFaces+wheels.length]; //firstly, create an array with its length is greater than the length of numFace
        for (int element :countTmp)                     //and is greater than the total of wheels in a Slot Machine. (countTmp array)
            element=0;                                  //all element of this array is 0
        for (int count=0;count< wheels.length;count++)  //counting the number of duplicated elements in indexFace array.
            countTmp[indexFace[count]]++;               //For instance, value of indexFace[count] appears countTmp[indexFace[count]] times
        int payOut=1;
        for (int count=0;count< numFaces + wheels.length;count++)
            if (countTmp[count]>0)             //Does not consider if an element in indexFace array does not appear (countTmp[count]< 0)
                if (countTmp[count]>=payOut)   //else, if countTmp[count] > 0 meaning elements in indexFace appeared countTmp[count] times
                    payOut=countTmp[count];    //comparing payOuts, set the larger payout if appear times of the next value is greater than previous values
        return payOut-1;    //payOut-1, because If at most two of them match, the payout is 1, if at most three match the payout is 2, and so on.
    }

    /**
     * The toString method reports the number of wheels and faces, the current state of each wheel, and the current payout.
     * @return a string text of the number of wheels and faces, the current state of each wheel, and the current payout.
     */
    @Override
    public String toString() {
        return "\t\tSlotMachine! ("+wheels.length+" wheels, "+numFaces+" faces)\n\t\t\twheels=["+payout();
    }

    /**
     * The drawWheels method can draw the text of selected faces of wheels in a Slot Machine on a GraphicsContextwhen
     * @param gc class GraphicsContext is used to issue draw calls to a Canvas using a buffer
     * */
    public void drawWheels(GraphicsContext gc, double locationX, double locationY){
        for (int nextWheel=20, count=0; count< wheels.length; count++,nextWheel+=30){
            gc.setStroke(this.colors[count]);                                           //set color for each text of selected faces
            gc.strokeText(this.faceSelected[count],locationX,locationY+nextWheel);  //draw with locationX and locationY (+30 for next text)
        }
    }

    /**
     * The histogram method of SlotMachine spins the wheels a million times by calling its own spinWheelsInSlotMachine method.
     * It uses an array of counters (countTmp) to record the number of times each payout comes up.
     * */
    public void histogram(){
        int[]spinArrays=new int[OCCURRENCES];           //firstly, create an array (spinArrays) to stored payouts of 1000000 spin times
        for (int count=0; count<OCCURRENCES;count++)    //and stored the value of each payout as an element of this spinArrays array.
            spinArrays[count]=payoutAlgorithm(spinWheelsInSlotMachine());   //calling spinWheelsInSlotMachine method to get payout for each spin
        int[] countTmp =new int[OCCURRENCES];            //secondly, the same payoutAlgorithm method, creating an array with its elements are 0
        for (int element : countTmp)
            element=0;
        for (int count=0; count<OCCURRENCES;count++)    //counting the number of duplicated elements in spinArrays array.
            countTmp[spinArrays[count]]++;              //For instance, value of spinArrays[count] appears countTmp[spinArrays[count]] times
        String statistics="\t\tPayout\t\tOccurrences\t\tChart";  //Print the header of Payout and Occurrences, stored them into a string statistics
        for (int count=0; count<OCCURRENCES; count++)   //Does not consider if an element in spinArrays array does not appear (countTmp[count]< 0)
            if (countTmp[count]>0) {                    //else, if countTmp[count] > 0 meaning elements in spinArrays appeared countTmp[count] times
                statistics+="\n\t\t\t"+count+"\t\t\t"+countTmp[count]+"\t\t"+(countTmp[count]<100 ? "\t*" : (countTmp[count]<1000 ? "\t**" : "***"));
                for(int star=0; star< Math.round((double) countTmp[count]/10000); star++)
                    statistics+="*";                    //print list of payouts, their occurrences and stars for charts
            }
        System.out.println(statistics);
    }
}
