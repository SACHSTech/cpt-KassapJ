package cpt.JavaCode;

import java.io.File;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
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
        Button button4;
        boolean isDataSorted;
        private ArrayList <Song> songs;
        private ArrayList <ListenEvent> listenEvents;
        Scene homepage, scene2, tableScene, graph1, graph2;
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
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File csvLocation = new File("ConvertedFiles");
        File jsonsLocation = new File("SpotifyJsonFilesHERE");
        File[] csvs = csvLocation.listFiles();
        File[] jsons = jsonsLocation.listFiles();

        if(csvs.length == 0 && jsons.length > 0){
            converter.convert();
        }
        else if(jsons.length <= 0){
            System.out.print("Please drag your jsons in");
        }

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

        // Button 3
        button4 = new Button("Scene 4:");
        button4.setOnAction(e -> window.setScene(graph2));

        // Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        layout2.getChildren().add(sortData);
        layout2.setAlignment(button2, Pos.TOP_RIGHT);
        layout2.getChildren().add(button3);
        layout2.setAlignment(button3, Pos.TOP_LEFT);
        layout2.getChildren().add(button4);
        layout2.setAlignment(button4, Pos.BOTTOM_LEFT);
        scene2 = new Scene(layout2, 1280, 720);

        //======== Table of values scene =========
        // Create an arraylist to hold the sorted data

        BorderPane layout4 = new BorderPane();
        StackPane tables = new StackPane();

        layout4.setCenter(tables);

        //Hbox for top section
        HBox tableTopSection = new HBox();
        Label label = new Label("TABLE VIEWER");
        tableTopSection.getChildren().add(label);
        layout4.setTop(tableTopSection);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Songs", "ListenEvents");
        String selectedOption = choiceBox.getValue();
        tableTopSection.getChildren().add(choiceBox);
        
            // Create SONGS table of values
            TableView songTable = new TableView<Song>();

            // format columns
            TableColumn songNameColumn = new TableColumn<Song, String>("Song Name");
            songNameColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));

            TableColumn artistNameColumn = new TableColumn<Song, String>("Artist Name");
            artistNameColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));

            TableColumn msListenedColumn = new TableColumn<Song, Integer>("MsListened");
            msListenedColumn.setCellValueFactory(new PropertyValueFactory<Song, Integer>("msListened"));

            TableColumn yearListenedColumn = new TableColumn<ListenEvent, Integer>("yearListened");
            yearListenedColumn.setCellValueFactory(new PropertyValueFactory<ListenEvent, Integer>("yearListened"));

            TableColumn monthListenedColumn = new TableColumn<ListenEvent, Integer>("monthListened");
            monthListenedColumn.setCellValueFactory(new PropertyValueFactory<ListenEvent, Integer>("monthListened"));

            TableColumn dayListenedColumn = new TableColumn<ListenEvent, Integer>("dayListened");
            dayListenedColumn.setCellValueFactory(new PropertyValueFactory<ListenEvent, Integer>("dayListened"));

            TableColumn hourListenedColumn = new TableColumn<ListenEvent, Integer>("hourListened");
            hourListenedColumn.setCellValueFactory(new PropertyValueFactory<ListenEvent, Integer>("hourListened"));

            TableColumn minuteListenedColumn = new TableColumn<ListenEvent, Integer>("minuteListened");
            minuteListenedColumn.setCellValueFactory(new PropertyValueFactory<ListenEvent, Integer>("minuteListened"));

            // add columns
            songTable.getColumns().add(songNameColumn);
            songTable.getColumns().add(artistNameColumn);
            songTable.getColumns().add(msListenedColumn);

            // add our already sorted data
            for(int i = 0; i < songs.size() - 1; i++){
                songTable.getItems().add(songs.get(i));
                //System.out.println(songs.get(i).getSongName());
            }

            // Create LISTEN EVENT table of values
            TableView listenEventTable = new TableView<ListenEvent>();

            // add columns
            listenEventTable.getColumns().add(songNameColumn);
            listenEventTable.getColumns().add(artistNameColumn);
            listenEventTable.getColumns().add(msListenedColumn);
            listenEventTable.getColumns().add(yearListenedColumn);
            listenEventTable.getColumns().add(monthListenedColumn);
            listenEventTable.getColumns().add(dayListenedColumn);
            listenEventTable.getColumns().add(hourListenedColumn);
            listenEventTable.getColumns().add(minuteListenedColumn);

            // add our already sorted data
            for(int i = 0; i < listenEvents.size() - 1; i++){
               listenEventTable.getItems().add(listenEvents.get(i));
            }
            
            tables.getChildren().add(songTable);
            tables.getChildren().add(listenEventTable);

            // Show the first table by default
            songTable.setVisible(true);
            listenEventTable.setVisible(false);

            // Add event handler to switch between the tables when the selection box value changes
            choiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Songs")) {
                songTable.setVisible(true);
                listenEventTable.setVisible(false);
            } else if (newValue.equals("ListenEvents")) {
                songTable.setVisible(false);
                listenEventTable.setVisible(true);
            }
        });

            // Create textbox to search for what we want in our songs
            TextField textField = new TextField();
            String enteredText = textField.getText();
        
        

        tableScene = new Scene(layout4, 1280, 720);



        //====== SECOND SCREEN WITH FIRST GRAPH======
        //===========================================
        //===========================================
        //===========================================
        //===========================================

        // This graph should show how often you listen to music at certain months, days, hours..
        
        int eventsJan = 0;
        int eventsFeb = 0;
        int eventsMar = 0;
        int eventsApr = 0;
        int eventsMay = 0;
        int eventsJun = 0;
        int eventsJul = 0;
        int eventsAug = 0;
        int eventsSep = 0;
        int eventsOct = 0;
        int eventsNov = 0;
        int eventsDec = 0;

        for(int i = 0; i < listenEvents.size() - 1; i++){
            int tempMonth = listenEvents.get(i).getMonthListened();
            if(tempMonth == 1){
                eventsJan++;
            }
            else if(tempMonth == 2){
                eventsFeb++;
            }
            else if(tempMonth == 3){
                eventsMar++;
            }
            else if(tempMonth == 4){
                eventsApr++;
            }
            else if(tempMonth == 5){
                eventsMay++;
            }
            else if(tempMonth == 6){
                eventsJun++;
            }
            else if(tempMonth == 7){
                eventsJul++;
            }
            else if(tempMonth == 8){
                eventsAug++;
            }
            else if(tempMonth == 9){
                eventsSep++;
            }
            else if(tempMonth == 10){
                eventsOct++;
            }
            else if(tempMonth == 11){
                eventsNov++;
            }
            else if(tempMonth == 12){
                eventsDec++;
            }

        }
        int[] timesPerMonth = {eventsJan, eventsFeb, eventsMar, eventsApr, eventsMay, eventsJun, eventsJul, eventsAug, eventsSep, eventsOct, eventsNov, eventsDec};
        
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        final NumberAxis lineXAxis = new NumberAxis();
        final NumberAxis lineYAxis = new NumberAxis();
        lineXAxis.setLabel("Months");
        lineYAxis.setLabel("Times listened");

        final LineChart<Number, Number> lineChart = new LineChart<>(lineXAxis, lineYAxis);
        lineChart.setTitle("Amount of songs listened per month the last year");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Monthly");
        for(int i = 0; i < months.length ; i++){
            series.getData().add(new XYChart.Data<>(i, timesPerMonth[i]));
        }

        lineChart.getData().add(series);

        BorderPane graphShower1 = new BorderPane();
        HBox graph1Top = new HBox();   
        graphShower1.setTop(graph1Top);
        graphShower1.setCenter(lineChart);

        // Add title to hbox
        Text graph1Title = new Text("Graph #1");
        graph1Title.setFont(font);
        graph1Title.setStrokeWidth(0);
        graph1Title.setFill(Color.BLACK);
        graph1Top.getChildren().add(graph1Title);
        
        graph1 = new Scene(graphShower1, 1280, 720);
        
        //====== THIRD SCREEN WITH SECOND GRAPH======
        //===========================================
        //===========================================
        //===========================================
        //===========================================


        final CategoryAxis barXAxis = new CategoryAxis();
        final NumberAxis barYAxis = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<>(barXAxis, barYAxis);

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Data Set 1");
        for(int i = 0; i < songs.size() - 1; i++){
            series1.getData().add(new XYChart.Data<>(songs.get(i).getSongName(), songs.get(i).getMsListened()));
        }
        //series1.getData().add(new XYChart.Data<>("Item 2", 80));
        //series1.getData().add(new XYChart.Data<>("Item 3", 60));
        //series1.getData().add(new XYChart.Data<>("Item 4", 75));

        barChart.getData().add(series1);
        barChart.setTitle("Bar Graph Example");
        barXAxis.setLabel("Items");
        barYAxis.setLabel("Values");

        graph2 = new Scene(barChart, 1280, 720);

        // Showing scene to stage
        window.setScene(homepage);
        window.show();
    }

    @Override
    public void handle(ActionEvent event){
    }

}
