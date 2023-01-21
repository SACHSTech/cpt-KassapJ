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
import javafx.scene.layout.StackPane;
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
        Button sortData;
        Button button1;
        Button button2;
        Scene homepage, scene2;
        dataSorter data = new dataSorter("ConvertedFiles/data.csv");


        public static void main(String[] args) {
        launch(args);
    }

    @Override 
    public void start(Stage window) throws Exception {
        // Fonts and sizes
        Font font = Font.loadFont("file:Resources/fonts/coolvetica.otf", 45);

        // Window Setup
        Image icon = new Image("file:resources/images/icon.png");
        window.getIcons().add(icon);
        window.setTitle("Spotify Data Visualizer");
        
        // ========= THIS IS THE HOMEPAGE =============
        // Grid Alignment setup
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(0,0,0,0));
        root.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 50), new CornerRadii(0), new Insets(0))));

        // Title text
        Text homepageTitle = new Text("Spotify Data Visualizer");
        homepageTitle.setFont(font);
        homepageTitle.setStrokeWidth(0);
        homepageTitle.setFill(Color.WHITE);
        root.add(homepageTitle, 0, 0);

        //button
        button1 = new Button();
        button1.setText("Switch to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));
        root.add(button1, 1, 5);

        //button to sort data
        sortData = new Button();
        sortData.setText("Sort my data");
        sortData.setOnAction(e -> data.sort());

        // Creating scene
        homepage = new Scene(root, 1280, 720);
    
        //====== THIS IS THE FIRST GRAPH=========
        // Button 2
        button2 = new Button("Scene 1");
        button2.setOnAction(e -> window.setScene(homepage));

        // Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        layout2.getChildren().add(sortData);
        layout2.setAlignment(button2, Pos.TOP_RIGHT);
        scene2 = new Scene(layout2, 1280, 720);

        // Showing scene to stage
        window.setScene(homepage);
        window.show();
    }

    @Override
    public void handle(ActionEvent event){
    }

}
