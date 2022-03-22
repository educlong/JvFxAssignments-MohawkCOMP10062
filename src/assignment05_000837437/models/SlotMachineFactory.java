package assignment05_000837437.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * Implementation of a Slot Machine has a set of Wheel objects in a array.
 * Each Wheel object has a different color, but each has the same set of faces.
 * A Slot Machine can spin its wheels, and can report the current payout.
 * It has a histogram method to spin the wheels a million times.
 * This is the class header.
 *
 * @date March 8, 2021
 * @author DUC LONG NGUYEN (Paul)
 *
 */
public class SlotMachineFactory {
    /**A Slot Machine can spin a OCCURRENCES times to make a histogram*/
    public static final int OCCURRENCES=1000000;
    /**The wheels of a SlotMachine stored in an array **/
    private WheelFactory[] wheelFactories;
    /**The colors of wheels in a Slot Machine stored in an array **/
    private Color[] colors;
    /**The number of faces of a wheel*/
    private int numFaces;
    /**The text of selected faces of wheels in a Slot Machine after spinning stored in an array **/
    private String[] faceSelected;

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
    public SlotMachineFactory(int numWheels, Color[] colors, int numFaces, String[] faces) {
        this.numFaces=numFaces;
        this.colors=new Color[numWheels];
        this.wheelFactories =new WheelFactory[numWheels];
        for (int count=0;count<numWheels;count++){
            this.colors[count]=colors[count];
            this.wheelFactories[count]=new WheelFactory(numFaces,faces,colors[count]);
        }
        this.faceSelected=new String[numWheels];
    }


    /**
     * The spinWheelsInSlotMachine method descripts that a Slot Machine can spin its wheels.
     * @return an integer array as the indexes of selected faces of a Slot Machine after spinning.
     */
    private int[] spinWheelsInSlotMachine(){
        int[] indexFaceSelected = new int[wheelFactories.length];
        for (int count = 0; count< wheelFactories.length; count++)                     //spin wheels in a array of wheels of a Slot Machine
            indexFaceSelected[count] = this.wheelFactories[count].indexSpinWheel(); //and return indexes of selected faces,
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
        for (int count = 0; count< wheelFactories.length; count++) {   //Stored the texts of selected faces after spinning into a faceSelected array
            this.faceSelected[count]=this.wheelFactories[count].getElementFace(indexFace[count]);
            faces += this.faceSelected[count] + (count == wheelFactories.length - 1 ? "]" : ", ");  //and print the current state of each wheel
        }
        return " => Payout="+payoutAlgorithm(indexFace);    //print the current state of each wheel and the current payout.
    }

    /**
     * This method is an algorithm which finds out the payout of a Slot Machine after spinning.
     * @param indexFace an integer array as the indexes of selected faces of a Slot Machine after spinning.
     * @return the payout number which is descriptive from the header of payout method.
     * */
    private int payoutAlgorithm(int[]indexFace){
        int[] countTmp =new int[numFaces+ wheelFactories.length]; //firstly, create an array with its length is greater than the length of numFace
        for (int element :countTmp)                     //and is greater than the total of wheels in a Slot Machine. (countTmp array)
            element=0;                                  //all element of this array is 0
        for (int count = 0; count< wheelFactories.length; count++)  //counting the number of duplicated elements in indexFace array.
            countTmp[indexFace[count]]++;               //For instance, value of indexFace[count] appears countTmp[indexFace[count]] times
        int payOut=1;
        for (int count = 0; count< numFaces + wheelFactories.length; count++)
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
        return "SlotMachine! ("+ wheelFactories.length+" wheels, "+numFaces+" faces)"+payout();
    }

    /**
     * The drawWheels method can draw the text of selected faces of wheels in a Slot Machine on a GraphicsContextwhen
     * @param gc class GraphicsContext is used to issue draw calls to a Canvas using a buffer
     * */
    public void drawWheels(GraphicsContext gc, double locationX, double locationY){
        for (int nextWheel = 20, count = 0; count< wheelFactories.length; count++,nextWheel+=30){
            gc.setStroke(this.colors[count]);                                           //set color for each text of selected faces
            gc.strokeText(this.faceSelected[count],locationX,locationY+nextWheel);  //draw with locationX and locationY (+30 for next text)
        }
    }

    /**
     * The histogram method of SlotMachine spins the wheels a million times by calling its own spinWheelsInSlotMachine method.
     * It uses an array of counters (countTmp) to record the number of times each payout comes up.
     *
     * @return the layout to display the histogram and the statistic of payouts after spinning a million time
     * */
    public VBox histogram(GraphicsContext gc, double locationX, double locationY){
        VBox layoutHistogram=new VBox();                    //create a layout to contain the histogram of payouts and the label of statistics of payouts
        layoutHistogram.setPrefSize(locationX,locationY);   //set preferred size for this layout
        CategoryAxis xAxis=new CategoryAxis();              //create x-axis for linechart
        NumberAxis yAxis=new NumberAxis();                  //create y-axis for linechart
        xAxis.setLabel("Payout (calculate from wheels)");   //set the title for the x-axis of this linechart
        yAxis.setLabel("Occurrences");                      //set the title for the y-axis of this linechart
        LineChart<String,Number> lineChartHistogram = new LineChart<String,Number>(xAxis,yAxis); //create a linechart contains the x-axis and y-axis above
        lineChartHistogram.setPrefSize( locationX, locationY-200);  //set preferred size for this linechart
        gc.clearRect(0, 0, locationX,locationY-200);        //clear for new screen
        lineChartHistogram.setTitle("Histogram of payout");             //set title for this linechart

        XYChart.Series<String,Number> seriesHistogram = new XYChart.Series<>();  //create a data set of this linechart, then store into seriesHistogram
        int[]spinArrays=new int[OCCURRENCES];           //firstly, create an array (spinArrays) to stored payouts of 1000000 spin times
        for (int count=0; count<OCCURRENCES;count++)    //and stored the value of each payout as an element of this spinArrays array.
            spinArrays[count]=payoutAlgorithm(spinWheelsInSlotMachine());   //calling spinWheelsInSlotMachine method to get payout for each spin
        int[] countTmp =new int[OCCURRENCES];            //secondly, the same payoutAlgorithm method, creating an array with its elements are 0
        for (int element : countTmp)
            element=0;
        for (int count=0; count<OCCURRENCES;count++)    //counting the number of duplicated elements in spinArrays array.
            countTmp[spinArrays[count]]++;              //For instance, value of spinArrays[count] appears countTmp[spinArrays[count]] times
        String statistics="\t\tPayout\t\tOccurrences";  //Print the header of Payout and Occurrences, stored them into a string statistics
        for (int count=0; count<OCCURRENCES; count++)   //Does not consider if an element in spinArrays array does not appear (countTmp[count]< 0)
            if (countTmp[count]>0) {                    //else, if countTmp[count] > 0 meaning elements in spinArrays appeared countTmp[count] times
                seriesHistogram.getData().add(new XYChart.Data<String,Number>(count+"",countTmp[count]));   //add data of chart to the data set
                statistics+="\n\t\t\t"+count+"\t\t\t"+countTmp[count];  //and print list of payouts
            }
        seriesHistogram.setName("Payout line");             //set name for the line of this histogram
        lineChartHistogram.getData().add(seriesHistogram);  //add seriesHistogram for a line of this histogram

        Label lblStatistic=new Label();             //label to display the statistic of payouts after spinning a million time
        lblStatistic.relocate(locationX,220);
        lblStatistic.setText(statistics);           //display statistic
        layoutHistogram.getChildren().addAll(lineChartHistogram,lblStatistic);  //add the histogram and statistic to layout
        return layoutHistogram;
    }
}