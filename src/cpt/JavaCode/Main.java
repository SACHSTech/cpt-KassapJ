package cpt.JavaCode;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Slider;
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
        Button homepageButton;
        Button tableButton;

        Button button;
        Button button1;
        Button button2;
        Button button3;
        Button button4;
        boolean isDataSorted;

        int windowWidth = 1280;
        int windowHeight = 720;
        private ArrayList <Song> songs;
        private ArrayList <ListenEvent> listenEvents;
        Scene homepage, scene2, tableScene, graph1, graph2, dataScene;
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
        Font tabFont = Font.loadFont("file:Resources/fonts/coolvetica.otf", 20);

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

        ChoiceBox<String> tableSelection = new ChoiceBox<>();
        tableSelection.getItems().addAll("Songs", "ListenEvents");
        String selectedOption = tableSelection.getValue();
        tableTopSection.getChildren().add(tableSelection);
        
            // Create SONGS table of values
            TableView songTable = new TableView<Song>();

            // Create LISTEN EVENT table of values
            TableView listenEventTable = new TableView<ListenEvent>();

            // formatting columns for Song objects
            TableColumn songNameColumn = new TableColumn<Song, String>("Song Name");
            songNameColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));

            TableColumn artistNameColumn = new TableColumn<Song, String>("Artist Name");
            artistNameColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));

            TableColumn msListenedColumn = new TableColumn<Song, Integer>("MsListened");
            msListenedColumn.setCellValueFactory(new PropertyValueFactory<Song, Integer>("msListened"));

            // Formatting columns for listenEvents
            TableColumn songNameColumn2 = new TableColumn<Song, String>("Song Name");
            songNameColumn2.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));

            TableColumn artistNameColumn2 = new TableColumn<Song, String>("Artist Name");
            artistNameColumn2.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));

            TableColumn msListenedColumn2 = new TableColumn<Song, Integer>("MsListened");
            msListenedColumn2.setCellValueFactory(new PropertyValueFactory<Song, Integer>("msListened"));

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

            ObservableList songItems = FXCollections.observableArrayList();
            // add our already sorted data
            for(int i = 0; i < songs.size(); i++){
                songItems.add(songs.get(i));
            }  

            songTable.setItems(songItems);


            // add columns
            listenEventTable.getColumns().add(songNameColumn2);
            listenEventTable.getColumns().add(artistNameColumn2);
            listenEventTable.getColumns().add(msListenedColumn2);
            listenEventTable.getColumns().add(yearListenedColumn);
            listenEventTable.getColumns().add(monthListenedColumn);
            listenEventTable.getColumns().add(dayListenedColumn);
            listenEventTable.getColumns().add(hourListenedColumn);
            listenEventTable.getColumns().add(minuteListenedColumn);

            // add our already sorted data
            ObservableList listenEventItems = FXCollections.observableArrayList();

            for(int i = 0; i < listenEvents.size() - 1; i++){
                listenEventItems.add(listenEvents.get(i));
            }

            listenEventTable.setItems(listenEventItems);
            
            tables.getChildren().add(songTable);
            tables.getChildren().add(listenEventTable);

            // SWITCHING BETWEEN TABLES
            // Show the first table by default
            songTable.setVisible(true);
            listenEventTable.setVisible(false);

            // Add event handler to switch between the tables when the selection box value changes
            tableSelection.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Songs")) {
                songTable.setVisible(true);
                listenEventTable.setVisible(false);
            } else if (newValue.equals("ListenEvents")) {
                songTable.setVisible(false);
                listenEventTable.setVisible(true);
            }

        });

            // SORTING DATA BASED ON BOX
            // SORTING SONGS =======
            ChoiceBox<String> tableSongSortSelection = new ChoiceBox<>();
            tableSongSortSelection.getItems().addAll("Song Name A-Z", "Song Name Z-A", "Artist Name A-Z", "Artist Name Z-A", "MsListened least-greatest", "MsListened greatest-least");
            String selectedSongSort = tableSelection.getValue();
            tableTopSection.getChildren().add(tableSongSortSelection);

            // Run a string sort or int sort for song depending on what is chosen
            tableSongSortSelection.valueProperty().addListener((observable, oldValue, newValue) -> {
                int tempIndex = 0;
                ArrayList<Song> tempArrayList = new ArrayList<Song>();

                if (newValue.equals("Song Name A-Z")) {
            
                    for(int i = 0; i < songs.size(); i++){
                        tempIndex = Sorting.songsBinarySearchSort(tempArrayList, songs.get(i).getArtistName(), songs.get(i).getMsListened());
                        if(tempIndex >= 0){
                            tempArrayList.add(tempIndex, new Song(songs.get(i).getMsListened(), songs.get(i).getArtistName(), songs.get(i).getSongName(), (songs.get(i).getSongName() + songs.get(i).getArtistName())));
                        }
                    }
                    System.out.println("got here");
                    songs = tempArrayList;

                songItems.clear();
                // Add newly sorted data
                for(int i = 0; i < songs.size(); i++){
                    songItems.add(songs.get(i));
                }  
    
                songTable.setItems(songItems);

                } else if (newValue.equals("ListenEvents")) {
                    songTable.setVisible(false);
                    listenEventTable.setVisible(true);
                }
            });



            // Textfield to search for SONG/LISTENEVENT based on name
            TextField searchBar = new TextField();

            searchBar.textProperty().addListener((observable,oldValue,newValue) -> {
                ObservableList tempSongItems = FXCollections.observableArrayList();
                ObservableList tempListenEventItems = FXCollections.observableArrayList();
                for(int i = 0; i < songs.size(); i++){
                    if((songs.get(i).getSongName()).toLowerCase().contains(newValue.toLowerCase())){
                        tempSongItems.add(songs.get(i));
                    }
                }
                for(int i = 0; i < listenEvents.size(); i++){
                    if((listenEvents.get(i).getSongName()).toLowerCase().contains(newValue.toLowerCase())){
                        tempListenEventItems.add(listenEvents.get(i));
                    }
                }

                songTable.setItems(tempSongItems);
                listenEventTable.setItems(tempListenEventItems);
            });

            // Allowing to click and view a SONG element

            songTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                Song selectedSong = (Song) newValue;
                if (selectedSong != null) {
                    //====== DATA DISPLAY  ======
                    //===========================================
                    //===========================================
                    //===========================================
                    //===========================================

                    BorderPane dataDisplay = new BorderPane();

                    //TabsMenu
                    VBox tabsMenu = new VBox();
                    Text tabText = new Text("Tabs");
                    tabText.setFont(tabFont);
                    tabsMenu.getChildren().add(tabText);

                    // Homepage Buttton
                    homepageButton = new Button("Homepage");
                    homepageButton.setOnAction(e -> window.setScene(homepage));

                    // TableViewer
                    tableButton = new Button("Table of Data");
                    tableButton.setOnAction(e -> {window.setScene(tableScene);});

                    //Add buttons to tab
                    tabsMenu.getChildren().addAll(homepageButton, tableButton);

                    // Adding tabs to scene
                    dataDisplay.setLeft(tabsMenu);

                    // Creating data display
                    GridPane dataHolder = new GridPane();
                    dataHolder.setPadding(new Insets(10, 10, 10, 10));
                    dataHolder.setVgap(8);
                    dataHolder.setHgap(10);

                    // Adding the data
                    // Artist Name 
                    Text artistNameText = new Text("Artist Name: " + selectedSong.getArtistName());
                    artistNameText.setFont(font);
                    dataHolder.setConstraints(artistNameText, 0, 0);
                    // Song Name
                    Text songNameText = new Text("Song Name: " + selectedSong.getSongName());
                    songNameText.setFont(font);
                    dataHolder.setConstraints(songNameText, 0, 1);
                    // Minutes Listened
                    Text minutesListenedText = new Text("Minutes Listened: " + selectedSong.getMinutesListened());
                    minutesListenedText.setFont(font);
                    dataHolder.setConstraints(minutesListenedText, 0, 2);
                
                
                    dataHolder.getChildren().addAll(artistNameText, songNameText, minutesListenedText);

                    dataDisplay.setCenter(dataHolder);

                    dataScene = new Scene(dataDisplay, windowWidth, windowHeight);
                    window.setScene(dataScene);
                }
            }
            );

            listenEventTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                ListenEvent selectedListenEvent = (ListenEvent) newValue;
                if (selectedListenEvent != null) {
                    //====== DATA DISPLAY  ======
                    //===========================================
                    //===========================================
                    //===========================================
                    //===========================================

                    BorderPane dataDisplay = new BorderPane();

                    //TabsMenu
                    VBox tabsMenu = new VBox();
                    Text tabText = new Text("Tabs");
                    tabText.setFont(tabFont);
                    tabsMenu.getChildren().add(tabText);

                    // Homepage Buttton
                    homepageButton = new Button("Homepage");
                    homepageButton.setOnAction(e -> window.setScene(homepage));

                    // TableViewer
                    tableButton = new Button("Table of Data");
                    tableButton.setOnAction(e -> {window.setScene(tableScene);});

                    //Add buttons to tab
                    tabsMenu.getChildren().addAll(homepageButton, tableButton);

                    // Adding tabs to scene
                    dataDisplay.setLeft(tabsMenu);

                    // Creating data display
                    GridPane dataHolder = new GridPane();
                    dataHolder.setPadding(new Insets(10, 10, 10, 10));
                    dataHolder.setVgap(8);
                    dataHolder.setHgap(10);

                    // Adding the data
                    // Artist Name 
                    Text artistNameText = new Text("Artist Name: " + selectedListenEvent.getArtistName());
                    artistNameText.setFont(font);
                    dataHolder.setConstraints(artistNameText, 0, 0);
                    // Song Name
                    Text songNameText = new Text("Song Name: " + selectedListenEvent.getSongName());
                    songNameText.setFont(font);
                    dataHolder.setConstraints(songNameText, 0, 1);
                    // Minutes Listened
                    Text msListenedText = new Text("MsListened: " + selectedListenEvent.getMsListened());
                    msListenedText.setFont(font);
                    dataHolder.setConstraints(msListenedText, 0, 2);
                    // Date Listened
                    Text dateListenedText = new Text("Date Listened: " + selectedListenEvent.getDateListened());
                    dateListenedText.setFont(font);
                    dataHolder.setConstraints(dateListenedText, 0, 3);
                
                
                    dataHolder.getChildren().addAll(artistNameText, songNameText, msListenedText, dateListenedText);

                    dataDisplay.setCenter(dataHolder);

                    dataScene = new Scene(dataDisplay, windowWidth, windowHeight);
                    window.setScene(dataScene);
                }
            }
            );


            // Homepage BUtton
            button = new Button("Homepage");
            button.setOnAction(e -> window.setScene(homepage));
            tableTopSection.getChildren().addAll(button, searchBar);
        
        

        tableScene = new Scene(layout4, 1280, 720);

        
        //====== SECOND SCREEN WITH FIRST GRAPH======
        //===========================================
        //===========================================
        //===========================================
        //===========================================

        // Sort based on ms listened
        songs = data.sortSongsMsListened(songs);

        // This graph should show how often you listen to music at certain months, days, hours..
        
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int[] monthCount = new int[12];

        // Runs a loop and adds to an int array, the amount of listens where at every month
        for(int i = 0; i < listenEvents.size(); i++){
            monthCount[listenEvents.get(i).getMonthListened() - 1]++;
        } 

        // Define both axis'
        CategoryAxis lineXAxis = new CategoryAxis();
        NumberAxis lineYAxis = new NumberAxis();
        
        // Set labels
        lineXAxis.setLabel("Months");
        lineYAxis.setLabel("Times listened");

        LineChart lineChart = new LineChart<>(lineXAxis, lineYAxis);

        XYChart.Series series = new XYChart.Series<>();
        series.setName("Monthly");

        for(int i = 0; i < months.length; i++){
            series.getData().add(new XYChart.Data<>(months[i], monthCount[i]));
        }

        lineChart.getData().add(series);

        BorderPane graphShower1 = new BorderPane();
        HBox graph1Top = new HBox();   
        graphShower1.setTop(graph1Top);
        graphShower1.setCenter(lineChart);
        VBox graph1Left = new VBox();
        graphShower1.setLeft(graph1Left);

        // Homepage BUtton
        button = new Button("Homepage");
        button.setOnAction(e -> window.setScene(homepage));

        // Add title to hbox
        Text graph1Title = new Text("Graph #1");
        graph1Title.setFont(font);
        graph1Title.setStrokeWidth(0);
        graph1Title.setFill(Color.BLACK);
        graph1Top.getChildren().addAll(graph1Title, button);
        
        graph1 = new Scene(graphShower1, 1280, 720);
        
        //====== THIRD SCREEN WITH SECOND GRAPH======
        //===========================================
        //===========================================
        //===========================================
        //===========================================
        boolean reConvert = false;
        BorderPane graphShower2 = new BorderPane();
        HBox graph2Top = new HBox();   
        graphShower2.setTop(graph2Top);
        VBox graph2Left = new VBox();
        graphShower2.setLeft(graph2Left);

        final CategoryAxis barXAxis = new CategoryAxis();
        final NumberAxis barYAxis = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<>(barXAxis, barYAxis);

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Data Set 1");
        for(int i = 0; i < songs.size() - 1; i++){
            series1.getData().add(new XYChart.Data<>(songs.get(i).getSongName(), songs.get(i).getMsListened()));
        }

        barChart.getData().add(series1);
        barChart.setTitle("Bar Graph Example");
        barXAxis.setLabel("Items");
        barYAxis.setLabel("Values");

        // Homepage BUtton
        button = new Button("Homepage");
        button.setOnAction(e -> window.setScene(homepage));

        graph2Top.getChildren().addAll(button);

        graphShower2.setCenter(barChart);
        // Add button to sort based on ms listened


        

        // Creating sliders for bar graph
        Slider slider1 = new Slider(0, songs.size() - 1, 0);
        Slider slider2 = new Slider(0, songs.size() - 1, 0);

        // Convert button
        Button convertButton = new Button();
        convertButton.setText("Show Changes");
        convertButton.setOnAction(e -> {series1.getData().clear();
            for(int i = (int)slider1.getValue(); i < (int)slider2.getValue(); i++){
                series1.getData().add(new XYChart.Data<>(songs.get(i).getSongName(), songs.get(i).getMsListened()));
            }
        });

        graph2Left.getChildren().addAll(slider1, slider2, convertButton);

        
        

    


        graph2 = new Scene(graphShower2, 1280, 720);

    

        // ============== Showing scene to stage ==================
        window.setScene(homepage);
        window.show();
    }

    @Override
    public void handle(ActionEvent event){
    }

}
