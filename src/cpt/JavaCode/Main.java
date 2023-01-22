package cpt.JavaCode;

import java.util.ArrayList;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
        Button button3;
        boolean isDataSorted;
        private ArrayList <Song> songs;
        private ArrayList <ListenEvent> listenEvents;
        Scene homepage, scene2, tableScene, graph1;
        dataSorter data = new dataSorter("ConvertedFiles/data.csv");
        JsonToCsv converter = new JsonToCsv();

        public static void main(String[] args) {
        launch(args);
    }

    
    @Override 
    public void start(Stage window) throws Exception {
        // initialize object lists
       songs = new ArrayList<Song>();
       listenEvents = new ArrayList<ListenEvent>();
        
        // Sort the csv
        //converter.convert();
        data.sort();
        songs = data.getSongs();
        listenEvents = data.getListenEvents();

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

        // Creating scene
        homepage = new Scene(root, 1280, 720);
    
        //====== FIRST SCREEN THAT HAS BUTTON TO SORT DATA =========
        // Eventually add a drag and drop for the folder

        //button to sort data
        sortData = new Button();
        sortData.setText("Sort my data");
        sortData.setOnAction(e -> {
            
            window.setScene(tableScene);
        });

        // Button 2
        button2 = new Button("Scene 1");
        button2.setOnAction(e -> window.setScene(homepage));

        // Button 3
        button3 = new Button("Scene 3:");
        button3.setOnAction(e -> window.setScene(graph1));

        // Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        layout2.getChildren().add(sortData);
        layout2.setAlignment(button2, Pos.TOP_RIGHT);
        layout2.getChildren().add(button3);
        layout2.setAlignment(button3, Pos.TOP_LEFT);
        scene2 = new Scene(layout2, 1280, 720);

        //======== Table of values scene =========
        // Create an arraylist to hold the sorted data

        BorderPane layout4 = new BorderPane();

        // Create table of values
        TableView table = new TableView<Song>();

        // format columns
        TableColumn songNameColumn = new TableColumn<Song, String>("Song Name");
        songNameColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));

        TableColumn artistNameColumn = new TableColumn<Song, String>("Artist Name");
        artistNameColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));

        TableColumn msListenedColumn = new TableColumn<Song, Integer>("MsListened");
        msListenedColumn.setCellValueFactory(new PropertyValueFactory<Song, Integer>("msListened"));

        // add columns
        table.getColumns().add(songNameColumn);
        table.getColumns().add(artistNameColumn);
        table.getColumns().add(msListenedColumn);

        // add our already sorted data
        for(int i = 0; i < songs.size() - 1; i++){
            table.getItems().add(songs.get(i));
        }
        layout4.setCenter(table);
        tableScene = new Scene(layout4, 1280, 720);



        //====== SECOND SCREEN WITH FIRST GRAPH======
        
        // First graph
        ArrayList<String> songNames = new ArrayList<String>();
        //String[] songNames = new String[songs.size()];
        ArrayList<Integer> songMsListened = new ArrayList<Integer>();

        for(int i = 0; i < songs.size() - 1; i++){
            //songNames[i] = songs.get(i).getSongName();
            songNames.add(i, songs.get(i).getSongName());
            songMsListened.add(i, songs.get(i).getMsListened());
        }

        BarChart chart;
        CategoryAxis xAxis;
        NumberAxis yAxis;

        xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(songNames));
        yAxis = new NumberAxis("MsListened", 0, 100000, 100000000);
        ObservableList<BarChart.Series> barChartData =
            FXCollections.observableArrayList(
                new BarChart.Series("Songs",
                                    FXCollections.observableArrayList(
                    new BarChart.Data(songs.get(0), songMsListened.get(0)),
                    new BarChart.Data(songs.get(1), songMsListened.get(1)),
                    new BarChart.Data(songs.get(2), songMsListened.get(2))
                )));

        chart = new BarChart(xAxis, yAxis, barChartData, 25);

        VBox layout3 = new VBox(chart);
        graph1 = new Scene(layout3, 1280, 720);

        //====== THIRD SCREEN WITH SECOND GRAPH======

        // Showing scene to stage
        window.setScene(homepage);
        window.show();
    }

    @Override
    public void handle(ActionEvent event){
    }

}
