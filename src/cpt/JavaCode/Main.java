package cpt.JavaCode;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*
 * This is the program that is going to create an object to get the data, which willc reate various other objects that can be serperated by attributes
 * This program however will take that data and be able to filter it out by different properties
 * This program will take that data and put it onto a data visualizer of some sort, probably one line chart and one bar graph
 * This program will also sort through it depending on what the user wants (least to greatest) etc.
 */

public class Main extends Application implements EventHandler<ActionEvent>{
        Button btn;
        Stage homepage;

        public static void main(String[] args) {
        launch(args);
    }

    @Override 
    public void start(Stage primaryStage) throws Exception {
        
        // Defining the primarystage as our homepage
        homepage = primaryStage;

        // Fonts and sizes
        Font font = Font.loadFont("file:Resources/fonts/coolvetica.otf", 45);

        // Window Setup
        Image icon = new Image("file:resources/images/icon.png");
        homepage.getIcons().add(icon);
        homepage.setTitle("Spotify Data Visualizer");
        
        // Grid Alignment setup
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0,0,0,0));

        //Rectangle
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(1280);
        rectangle.setHeight(100);
        grid.add(rectangle,0,0);

        // Title text
        Text homepageTitle = new Text("Spotify Data Visualizer");
        homepageTitle.setFont(font);
        homepageTitle.setStrokeWidth(0);
        homepageTitle.setFill(Color.WHITE);
        grid.add(homepageTitle, 1, 0);


        //button
        Button btn = new Button();
        btn.setText("Switch to scene 2");
        btn.setOnAction(this);
        grid.add(btn, 0, 5);

        // Creating scene
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 50), new CornerRadii(0), new Insets(0))));
        Scene scene = new Scene(grid, 1280, 720);

        // Showing scene to stage
        homepage.setScene(scene);
        homepage.show();
    }

    @Override
    public void handle(ActionEvent event){
        if(event.getSource() == btn){
            System.out.println("Swaggy");
        }
    }

}
