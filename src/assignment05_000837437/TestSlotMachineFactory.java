package assignment05_000837437;

import assignment05_000837437.models.SlotMachineFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *  Program draw a single screen from the game Slot Machine, configured according to user input.
 * This is the class header.
 *
 * @date March 8, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class TestSlotMachineFactory extends Application{
    final int WIDTH_OF_WINDOW=550,  HEIGHT_OF_WINDOW=550;
    final int HEIGHT_OF_NORTH=HEIGHT_OF_WINDOW/10, WIDTH_OF_EAST=WIDTH_OF_WINDOW/2;
    final int PREF_SIZE=60, LOCATION_X=10, LOCATION_Y=15;
    final int FONT_SIZE=20;
    final String FONT_STYLE="Times New Roman";
    Stage window;
    Canvas canvas;
    Label lblTitle;
    TextField txtnumFaces, txtnumWheels;
    CheckBox chkMonochrome;
    Button btnCreateFaces, btnHistogram, btnClear;
    SlotMachineFactory slotMachine;
    Pane layoutCenter,layoutEast;
    Label lblTextFaces;
    FlowPane layoutTextFace;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        BorderPane layoutMainRoot=new BorderPane();
        window.setTitle("Slot Machine Maker - Assignment05 - Duc Long Nguyen - 000837437");  // Set window title
        canvas = new Canvas(WIDTH_OF_EAST, HEIGHT_OF_WINDOW-HEIGHT_OF_NORTH);       // Set canvas Size in Pixels
        Scene scMain =new Scene(layoutMainRoot, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        window.setScene(scMain);
        window.setResizable(false);                                                     //disable screen to expand
        addControls(layoutMainRoot);                                                    //
        window.show();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // This code starts a "thread" which will run your animation
        Thread t = new Thread(() -> animate(gc));
        t.start();
    }
    public void animate(GraphicsContext gc) {
        gc.setFont(Font.font(FONT_STYLE, FONT_SIZE));
        gc.setLineWidth(2);
        // *** processing and output (Events)
        btnClear.setOnAction(event->{
            eventClearLayout();             //event for clearing texts of txtnumFaces, txtnumWheels and Pane of layoutTextFace
        });
        btnHistogram.setOnAction(event ->{
            eventDrawHistogram(gc);         //event for drawing a histogram
        });
        btnCreateFaces.setOnAction(event->{
            eventEnterTextForFaces(gc);     //event for entering the texts of faces
        });
    }

    /**
     * This method to create GUI Layout including controlled components such as Label, Button, TextField, Pane, etc,.
     *
     * @param layoutMainRoot Root layout which lays out children in top, left, right, bottom, and center positions.
     * */
    private void addControls(BorderPane layoutMainRoot) {
        Pane layoutNorth=new Pane();            //layout for title (the north of root layout)
        layoutMainRoot.setTop(layoutNorth);
        layoutEast=new Pane();                  //layout for displaying the results of showing (the east of root layout)
        layoutMainRoot.setRight(layoutEast);
        layoutCenter=new Pane();                //layout for displaying all controls for user (the center of root layout)
        layoutMainRoot.setCenter(layoutCenter);
        layoutNorth.setPrefSize(0,HEIGHT_OF_NORTH); //set preferred size
        layoutEast.setPrefSize(WIDTH_OF_EAST,0);
        layoutNorth.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
        layoutEast.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
        layoutCenter.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE,null,null)));
        controlsNorth(layoutNorth);             //processing for the north layout
        controlsCenters();                      //processing for the center layout
    }

    /**
     * This method to create the header Layout (the title)
     *
     * @param layoutNorth Root layout which lays out children in top, left, right, bottom, and center positions.
     * */
    private void controlsNorth(Pane layoutNorth) {
        lblTitle=new Label("Slot Machine!");
        lblTitle.setFont(new Font(FONT_STYLE,FONT_SIZE*2));
        lblTitle.relocate((int)(WIDTH_OF_WINDOW/4),(int)(HEIGHT_OF_NORTH/10));
        layoutNorth.getChildren().add(lblTitle);     //Add label title into layout of north
    }

    /**
     * This method to create the center Layout (to display all controls for user)
     * */
    private void controlsCenters() {
        Label lblNumFaces=new Label("Enter the number of faces");
        Label lblNumWheels=new Label("Enter the number of Wheels");
        Label lblHistogram=new Label("(Random text of faces after spin a million times)");
        txtnumFaces=new TextField();                   //textfield to enter the number of faces
        txtnumWheels=new TextField();                  //textfield to enter the number of wheels
        chkMonochrome=new CheckBox("Monochrome");   //checkbox to check whether all of wheels have monochrome or not
        btnCreateFaces=new Button("Create Faces for a Wheel");  //click this button to create faces for a wheel
        btnHistogram=new Button("Histogram of payout");         //click this button to make a histogram of payout after spin for a million times
        btnClear=new Button("Clear");               //click this button to clear the text of txtnumFaces, txtnumWheels and layoutTextFace
        lblTextFaces=new Label();                      //Label to make an announcement: "Let's enter the text for each face!" after click btnCreateFaces
        layoutTextFace=new FlowPane();                 //Pane to enter the text for each face and Button spin slot machine

        txtnumFaces.setPrefSize(PREF_SIZE,0);       //set preferred size for these controls
        txtnumWheels.setPrefSize(PREF_SIZE,0);
        layoutTextFace.setPrefSize(WIDTH_OF_EAST,0);

        lblNumFaces.relocate(LOCATION_X,LOCATION_Y);    //set location for these controls
        lblNumWheels.relocate(LOCATION_X,LOCATION_Y+30);
        txtnumFaces.relocate(LOCATION_X+170,LOCATION_Y-8);
        txtnumWheels.relocate(LOCATION_X+170,LOCATION_Y+22);
        chkMonochrome.relocate(LOCATION_X,LOCATION_Y+60);
        btnCreateFaces.relocate(LOCATION_X,LOCATION_Y+90);
        btnHistogram.relocate(LOCATION_X,LOCATION_Y+120);
        lblHistogram.relocate(LOCATION_X,LOCATION_Y+150);
        btnClear.relocate(LOCATION_X,LOCATION_Y+180);
        lblTextFaces.relocate(LOCATION_X,LOCATION_Y+240);
        layoutTextFace.relocate(0,LOCATION_Y+270);

        layoutCenter.getChildren().addAll(lblNumFaces,lblNumWheels,txtnumFaces,txtnumWheels,chkMonochrome,
                btnCreateFaces,btnHistogram,lblHistogram,btnClear,lblTextFaces,layoutTextFace); //add all of these controls into layoutCenter
    }


    /**
     * event for clearing texts of txtnumFaces, txtnumWheels, lblTextFaces and Pane of layoutTextFace
     * */
    private void eventClearLayout() {
        txtnumFaces.setText("");
        txtnumWheels.setText("");
        lblTextFaces.setText("");
        chkMonochrome.setSelected(false);
        layoutTextFace.getChildren().clear();
        txtnumFaces.requestFocus();
    }

    /**
     * event for drawing a histogram of payout after spin for a million times
     *
     * @param gc The drawing surface
     * */
    private void eventDrawHistogram(GraphicsContext gc) {
        if (txtnumFaces.getText().toString().isEmpty() || txtnumWheels.getText().toString().isEmpty()) return;  //do nothing if these textfields are empty
        String[] faces=new String[Integer.parseInt(txtnumFaces.getText().toString())];              //create faces for a wheel and create a slotMachine object
        for (int count = 0; count < Integer.parseInt(txtnumFaces.getText().toString()); count++)
            faces[count] = count+"";
        slotMachine=new SlotMachineFactory(Integer.parseInt(txtnumWheels.getText().toString()),checkColor(),Integer.parseInt(txtnumFaces.getText().toString()),faces);
        layoutEast.getChildren().clear();   //clear the layout for displaying the results of showing
        layoutEast.getChildren().add(slotMachine.histogram(gc,WIDTH_OF_EAST, HEIGHT_OF_WINDOW-HEIGHT_OF_NORTH));    //calling histogram method to display.
    }

    /**
     * event for entering the texts of faces
     *
     * @param gc The drawing surface
     * */
    private void eventEnterTextForFaces(GraphicsContext gc) {
        if (txtnumFaces.getText().toString().isEmpty() || txtnumWheels.getText().toString().isEmpty()) return;  //do nothing if these textfields are empty
        lblTextFaces.setText("Let's enter the text for each face!");            //make an announcement
        layoutTextFace.getChildren().clear();       //before entering the text of faces, clear all of these.
        ObservableList<TextField> txtFaces= FXCollections.observableArrayList();                //create textFields for entering the text of each face,
        for (int count=0;count<Integer.parseInt(txtnumFaces.getText().toString());count++){     //store these textfields into a list named txtFaces
            TextField txtFace=new TextField();
            txtFace.setPrefSize(PREF_SIZE,0);
            txtFaces.add(txtFace);
            layoutTextFace.getChildren().add(txtFace);              //add these textfields into a layout
        }
        Label lblPayout=new Label();                                //label to display the value of payout after spin slot machine
        Button btnSlotMachine=new Button("Spin Slot Machine");   //click this button to spin slot machine
        layoutTextFace.getChildren().addAll(btnSlotMachine,lblPayout);
        btnSlotMachine.setOnAction(eventSlot ->{            //event for spinning all wheels of the slot machine!
            lblPayout.setText("");                          //before spinning, value of payout is empty
            layoutEast.getChildren().clear();               //before spinning, clear the layout (layout to display the results of showing)
            eventSpinSlotMachine(gc,txtFaces,lblPayout);    //method to spin all wheels of the slot machine
        });
    }

    /**
     * method to spin all wheels of the slot machine
     *
     * @param gc The drawing surface
     * @param txtFaces The list of TextField contains the texts of a face
     * @param lblPayout Label to display the value of payout after spin slot machine
     * */
    private void eventSpinSlotMachine(GraphicsContext gc, ObservableList<TextField> txtFaces,Label lblPayout) {
        layoutEast.getChildren().add(canvas);               //add canvas to layoutEast to draw before spinning
        String[]faces=new String[Integer.parseInt(txtnumFaces.getText().toString())];   //create a faces-array for a wheel after entering the text of each face,
        for (int count=0;count<faces.length;count++){
            faces[count]=txtFaces.get(count).getText().toString();
        }
        slotMachine=new SlotMachineFactory(Integer.parseInt(txtnumWheels.getText().toString()),checkColor(),Integer.parseInt(txtnumFaces.getText().toString()),faces);
        lblPayout.setText(slotMachine+"");                  //display the value of payout.
        gc.clearRect(0, 0, WIDTH_OF_EAST, HEIGHT_OF_WINDOW-HEIGHT_OF_NORTH);  //clear for new screen
        slotMachine.drawWheels(gc,LOCATION_X,LOCATION_Y);   //draw Slot Machine to screen through drawWheels method
    }

    /**
     * method to create a color-array from the number of wheels and the checkbox chkMonochrome
     * if checkbox is selected, all of wheels have the same color. Else, all of wheels have the different colors
     *
     * @return a array of color for a slot machine
     * */
    private Color[] checkColor() {
        Color[]colors=new Color[Integer.parseInt(txtnumWheels.getText().toString())];
        if (chkMonochrome.isSelected()) {           //if checkbox is selected, all of wheels have the same color
            int intColor=(int)(Math.random()*125);
            for (int count = 0; count < Integer.parseInt(txtnumWheels.getText().toString()); count++)
                colors[count] = Color.rgb(intColor,intColor,intColor);
        }
        else    //if checkbox is not selected, all of wheels have the different colors
            for (int count = 0; count < Integer.parseInt(txtnumWheels.getText().toString()); count++)
                colors[count] = Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
        return colors;
    }


    /**
     * Use this method instead of Thread.sleep(). It handles the possible
     * exception by catching it, because re-throwing it is not an option in this
     * case.
     *
     * @param duration Pause time in milliseconds.
     */
    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
        }
    }

    /**
     * Exits the app completely when the window is closed. This is necessary to
     * kill the animation thread.
     */
    @Override
    public void stop() {
        System.exit(0);
    }

    /**
     * Launches the app
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
