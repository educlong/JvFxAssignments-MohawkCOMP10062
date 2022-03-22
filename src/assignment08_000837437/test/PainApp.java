package assignment08_000837437.test;

import assignment08_000837437.models.Circle;
import assignment08_000837437.models.GeometricObject;
import assignment08_000837437.models.Square;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Program draw shapes (circle or square) from the description, configured according to user input.
 * This is the class header.
 *
 * @date April 3, 2021
 * @author DUC LONG NGUYEN (Paul)
 */
public class PainApp extends Application {

    /**
     * Start method (use this instead of main).
     *
     * @param primaryStage The FX stage to draw on
     * @param WIDTH_OF_WINDOW The width of screen
     * @param HEIGHT_OF_WINDOW The height of screen
     * @param HEIGHT_OF_NORTH The height of Title
     * @param HEIGHT_OF_SOUTH The height of control panel layout
     * @param PREF_SIZE The preferred size of a control
     * @param FONT_SIZE The size of font
     * @param FONT_STYLE_TITLE The font of the title
     * @param FONT_STYLE_ANNOUNCEMENT The font of the label announcement
     * @param BACKGROUND_COLOR The background color of the canvas
     * @param lblTitle The label for title of this program
     * @param layoutMainRoot The main layout for this program
     * @param btnCircle Button to choose a circle to draw
     * @param btnSquare Button to choose a square to draw
     * @param btnDraw Button to draw a shape
     * @param btnUnDraw Button to remove the last shape
     * @param btnEraser Button to clear all the shapes
     * @param txtXLocation TextField to type the x location of the shape
     * @param txtYLocation TextField to type the y location of the shape
     * @param txtSize TextField to type the size of the shape
     * @param txtRed TextField to type the red value of the color of the shape
     * @param txtGreen TextField to type the green value of the color of the shape
     * @param txtBlue TextField to type the blue value of the color of the shape
     * @param lblAnnouncement Label to make an announcement whether the input values are correct or not
     * @param flagCircleOrSquare flag to know user choose to draw circles or squares
     * @param shapes The arraylist that stores all shapes including the circle and the square.
     * @throws Exception
     */
    public static final int WIDTH_OF_WINDOW=1200,  HEIGHT_OF_WINDOW=750;
    public static final int HEIGHT_OF_SOUTH=HEIGHT_OF_WINDOW/10, HEIGHT_OF_NORTH=HEIGHT_OF_WINDOW/20;
    final int PREF_SIZE=100, FONT_SIZE=10, PADDING_TOP=5, PADDING_RIGHT=5,PADDING_BOTTOM=10, PADDING_LEFT=15;
    final String FONT_STYLE_TITLE="Times New Roman", FONT_STYLE_ANNOUNCEMENT ="Courier New";
    final Color BACKGROUND_COLOR=Color.WHITE;
    Stage window;
    Canvas canvas;
    Label lblTitle;
    BorderPane layoutMainRoot;
    Button btnCircle, btnSquare, btnDraw, btnUnDraw, btnEraser;
    TextField txtXLocation,txtYLocation, txtSize, txtRed,txtGreen,txtBlue;
    Label lblAnnouncement;
    GraphicsContext gc;
    boolean flagCircleOrSquare;         // flag to know user choose to draw circles or squares
    ArrayList<GeometricObject> shapes;  // The circles and squares which are drawn by the users are stored in an arraylist named shapes
    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        layoutMainRoot=new BorderPane();
        window.setTitle("Drawing - Assignment 08 - Duc Long Nguyen - 000837437");  // Set window title
        canvas = new Canvas(WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW-HEIGHT_OF_NORTH-HEIGHT_OF_SOUTH);
        Scene scMain =new Scene(layoutMainRoot, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        window.setScene(scMain);
        window.setResizable(false);         //disable screen to expand
        gc = canvas.getGraphicsContext2D();
        // ________ MY CODE GOES HERE ___________
        addControls();  // processing all controls (Label, TextField, Button, Pane, Canvas, etc,.)
        addEvents();    // processing events (event handles, Mouse events, etc,.)
        // ______________________________________
        window.show();
    }

    /**
     * This method to create GUI Layout including controlled components such as Label, Button, TextField, Pane, etc,.
     * */
    private void addControls() {
        shapes=new ArrayList<>();       // initialize an arraylist
        flagCircleOrSquare=true;        // by default, drawing circle
        Pane layoutNorth= new Pane();   // layout for title (the north of root layout)
        layoutMainRoot.setTop(layoutNorth);
        VBox layoutSouth=new VBox();    // layout for drawing
        layoutMainRoot.setBottom(layoutSouth);
        Pane layoutCenter=new Pane();   // layout for control panel
        layoutMainRoot.setCenter(layoutCenter);
        layoutNorth.setPrefSize(0,HEIGHT_OF_NORTH); // set preferred size
        layoutSouth.setPrefSize(0,HEIGHT_OF_SOUTH);
        layoutNorth.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE,null, null)));
        layoutCenter.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR,null, null)));
        layoutSouth.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE,null,null)));
        layoutCenter.getChildren().add(canvas); // processing for the center layout (add canvas to the center)
        controlsNorth(layoutNorth);             // processing for the north layout
        controlsSouth(layoutSouth);             // processing for the south layout
    }

    /**
     * This method to create the header Layout (the title)
     * @param layoutNorth Title layout
     * */
    private void controlsNorth(Pane layoutNorth) {
        lblTitle=new Label("Press Draw or Click Canvas for a Circle or Square");    //the title of this program
        lblTitle.setFont(new Font(FONT_STYLE_TITLE, FONT_SIZE*3));
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setPrefSize(WIDTH_OF_WINDOW,0);
        layoutNorth.getChildren().add(lblTitle);        //Add label title into layout of north
    }

    /**
     * This method to create the control panel Layout (to display all controls for user)
     * @param layoutSouth Control panel layout
     * */
    private void controlsSouth(VBox layoutSouth) {
        FlowPane layoutConfig=new FlowPane();       // pane contains the control panel
        btnCircle =new Button("Circle");        // click this button to choose to draw circles
        btnSquare=new Button("Square");         // click this button to choose to draw squares
        Label lblLocation=new Label(" Location (X,Y): ");    // Label to let user know to type the locations of a shape
        txtXLocation=new TextField();               // textField to type the x location of a shape
        txtYLocation=new TextField();               // textField to type the y location of a shape
        Label lblSize=new Label(" Size: ");     // Label to let user know to type the size of a shape
        txtSize=new TextField();                    // textField to type the size of a shape
        Label lblColor=new Label(" Color: ");   // Label to let user know to type the Color of a shape
        txtRed=new TextField();                     // textField to type the red value of the color of a shape
        txtGreen=new TextField();                   // textField to type the green value of the color of a shape
        txtBlue=new TextField();                    // textField to type the blue value of the color of a shape
        btnDraw=new Button("Draw");             // click this button to draw a shape
        btnUnDraw=new Button("UnDraw");         // click this button to remove the last shape
        Label lblEraser=new Label(" (Clear All)");  // Label to let user know to Clear the canvas
        btnEraser=new Button("Eraser");         // click this button to clear the canvas
        ArrayList<Object> controls=new ArrayList<>();   // Stores all the controls to an arraylist
        controls.add(btnCircle);
        controls.add(btnSquare);
        controls.add(lblLocation);
        controls.add(txtXLocation);
        controls.add(txtYLocation);
        controls.add(lblSize);
        controls.add(txtSize);
        controls.add(lblColor);
        controls.add(txtRed);
        controls.add(txtGreen);
        controls.add(txtBlue);
        controls.add(btnDraw);
        controls.add(btnUnDraw);
        controls.add(lblEraser);
        controls.add(btnEraser);
        btnUnDraw.setDisable(true);
        for (Object control : controls) {
            if (control instanceof TextField) {                 // set preferred size and the alignment for the textField
                ((TextField) control).setPrefSize(PREF_SIZE, 0);
                ((TextField) control).setAlignment(Pos.CENTER);
            }
            if (control instanceof Label)                       // set padding for the label
                ((Label) control).setPadding(new Insets(0,PADDING_RIGHT,0,PADDING_LEFT));
            layoutConfig.getChildren().add((Node) control);     // add these controls to control panel layout
        }
        layoutConfig.setAlignment(Pos.TOP_CENTER);              // set the alignment and the padding for the control panel layout
        layoutConfig.setPadding(new Insets(PADDING_TOP,0,PADDING_BOTTOM,0));

        lblAnnouncement=new Label("No Errors");             // textField to make an announcement whether the figures are correct or not
        lblAnnouncement.setPrefSize(WIDTH_OF_WINDOW, (double) (HEIGHT_OF_SOUTH/3));             // set preferred size for this label
        lblAnnouncement.setFont(Font.font(FONT_STYLE_ANNOUNCEMENT,FontWeight.BOLD,FONT_SIZE));  // set font for this label
        lblAnnouncement.setTextFill(Color.GREEN);
        lblAnnouncement.setAlignment(Pos.CENTER);
        lblAnnouncement.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
        layoutSouth.getChildren().addAll(layoutConfig,lblAnnouncement); // add the control pane layout and announcement label into layoutSouth
    }

    /**
     * This method to proceed all the events (event handles, mouse event, etc,.)
     * */
    private void addEvents() {
        btnCircle.setOnAction(event-> flagCircleOrSquare=true);                         // event handles to choose the circle for drawing
        btnSquare.setOnAction(event-> flagCircleOrSquare=false);                        // event handles to choose the square for drawing
        canvas.setOnMouseDragged(event -> eventDrawShape(event.getX(),event.getY()));   // mouse dragged event to draw a shape
        canvas.setOnMousePressed(event -> eventDrawShape(event.getX(),event.getY()));   // mouse pressed event to draw a shape
        btnDraw.setOnAction(event -> {                  // event handles to draw a shape
            try {
                eventDrawShape(Double.parseDouble(txtXLocation.getText()),Double.parseDouble(txtYLocation.getText()));  // process this event
            }catch (Exception ex){                      // catch an exception if the input values of the locations are wrong.
                lblAnnouncement.setTextFill(Color.RED);
                lblAnnouncement.setText("The locations are wrong ["+ex.getMessage()+"]. "); // make an announcement about this error
            }
        });
        btnUnDraw.setOnAction(event -> eventRemoveShape());                             // event handles to remove the last shape
        btnEraser.setOnAction(event -> eventRemoveAll());                               // event handles to clear the canvas
    }

    /**
     * This method to proceed the event which is for drawing a shape.
     * @param xLocation The x location of a shape
     * @param yLocation The y location of a shape
     * */
    private void eventDrawShape(double xLocation, double yLocation) {
        try {
            lblAnnouncement.setTextFill(Color.GREEN);   // set the color and text for the announcement label if it is not error
            lblAnnouncement.setText("No Errors");
            GeometricObject shapeObject = flagCircleOrSquare    // create a shape (if this flag==true, create a circle. Else, create a square
                    ? (new Circle(xLocation, yLocation,  Integer.parseInt(txtSize.getText()),
                    Color.rgb(Integer.parseInt(txtRed.getText()),Integer.parseInt(txtGreen.getText()),Integer.parseInt(txtBlue.getText()))))
                    : (new Square(xLocation, yLocation, Integer.parseInt(txtSize.getText()),
                    Color.rgb(Integer.parseInt(txtRed.getText()),Integer.parseInt(txtGreen.getText()),Integer.parseInt(txtBlue.getText()))));
            shapeObject.draw(gc);                   // draw the shape that was created above
            shapes.add(shapeObject);                // add this shape to an arraylist
            btnUnDraw.setDisable(false);            // enable the UnDraw Button
            txtXLocation.setText(xLocation+"");     // set x location
            txtYLocation.setText(yLocation+"");     // set y location
        } catch (NumberFormatException nfEx){       // catch an exception if the input values of the color are not numbers
            lblAnnouncement.setTextFill(Color.RED);
            lblAnnouncement.setText("The size or Color is wrong ["+nfEx.getMessage()+"]. ");    // make an announcement about this error
        } catch (IllegalArgumentException iaEx){    // catch an exception if the input values of the color are wrong numbers
            lblAnnouncement.setTextFill(Color.RED);
            lblAnnouncement.setText("Bad value ["+iaEx.getMessage()+"]. ");                     // make an announcement about this error
        }
    }

    /**
     * This method to proceed the event which is for removing a last shape.
     * */
    private void eventRemoveShape() {
        if (shapes.size()<=0) return;           // return if the shapes arraylist is empty
        btnUnDraw.setDisable(false);            // enable the UnDraw button if the shapes arraylist is not empty
        shapes.remove(shapes.size() - 1); // remove the last item in the shapes arraylist
        gc.setFill(BACKGROUND_COLOR);           // clear the canvas
        gc.fillRect(0,0,WIDTH_OF_WINDOW,HEIGHT_OF_WINDOW-HEIGHT_OF_NORTH-HEIGHT_OF_SOUTH);
        for (GeometricObject control : shapes)  // redraw the remaining shapes, for loop to draw each shape
            control.draw(gc);
        if (shapes.size() <= 0)                 // disable the UnDraw button if the shapes arraylist is empty
            btnUnDraw.setDisable(true);
    }

    /**
     * This method to proceed the event which is for clear all shapes on canvas
     * */
    private void eventRemoveAll() {
        gc.setFill(BACKGROUND_COLOR);   // clear the canvas
        gc.fillRect(0,0,WIDTH_OF_WINDOW,HEIGHT_OF_WINDOW-HEIGHT_OF_NORTH-HEIGHT_OF_SOUTH);
        shapes.clear();                 // clear all the shapes in the arraylist
        btnUnDraw.setDisable(true);     // disable the UnDraw button as the shapes arraylist is empty
    }
    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}