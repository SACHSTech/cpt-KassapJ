package cpt.JavaCode;

import java.util.ArrayList;
import java.io.*;

public class dataSorter{
    private String file;
    private ArrayList <Song> songs;
    private ArrayList <ListenEvent> listenEvents;
    public static final String delimiter = ",";

    public dataSorter(String fileLocation){
        this.file = fileLocation;
        this.songs = new ArrayList<Song>();
        this.listenEvents = new ArrayList<ListenEvent>();
    }

    public void sort(){

        try{
            File csvData = new File(file);
            FileReader fileReader = new FileReader(csvData);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String[] tempArray;
            String artistName = "";
            String songName = "";
            String msListened = "";
            String dateListened = "";
            String tempString2 = "";
            int songCode = 0;
            int count = 0;
            int count2test = 0;

            while((line = bufferedReader.readLine()) != null){
                tempArray = line.split(delimiter);
                count = 0;
                // This isn't useful to the code, just to see what's happening. This is where we would access the variables and add it to the object
                for(String tempString : tempArray){
                    // Temp string is the data that I'm at this current moment
                    if(count == 0){
                        msListened = tempString;
                    }
                    else if(count == 1){
                        artistName = tempString;
                    }
                    else if(count == 2){
                        dateListened = tempString;
                    }
                    else if(count == 3){
                        songName = tempString;
                    }
                    count++;
                }
                // Create songcode
                // to do the song code, go through a strings first 2 letters 
                // or as many as you can if it's less than 2, for both song title and artist
                // Turn the letter you get to a char, make it upper case and get the Dec for it, then add it to a code
                // do this and you'll get around a 8 digit code integer.
                //songCode = 
                // once you collect all the variables, run a binary search 
                //to find the right place to insert it based on the songcode
                //songs.add(new Song(msListened, artistName, songName, songCode))
                
                // Fix artist name and song name having € in it, and convert those to commas
                tempString2 = "";
                songCode = 0;
                for(int i = 0; i < artistName.length(); i++){
                    char ch = artistName.charAt(i);
                    if(ch == '€'){
                        tempString2 += ",";
                        ch = ',';
                    }
                    else{
                        tempString2 += ch;
                    }
                    // Making song code
                    if(i < 2){
                        songCode += (Character.toUpperCase(ch));
                        songCode *= 10;
                    }
                }
                artistName = tempString2;
                tempString2 = "";
                for(int i = 0; i < songName.length(); i++){
                    char ch = songName.charAt(i);
                    if(ch == '€'){
                        tempString2 += ",";
                        ch = ',';
                    }
                    else{
                        tempString2 += ch;
                    }
                    // Making songcode
                    if(i < 2){
                        songCode += (Character.toUpperCase(ch));
                        if(i < 1){
                            songCode *= 10;
                        }
                    }
                }
                songName = tempString2;

                listenEvents.add(new ListenEvent(msListened, artistName, dateListened, songName, songCode));
                songs.add(new Song(msListened, artistName, songName, songCode));
                System.out.println(listenEvents.get(count2test).getMsListened() + " - " + listenEvents.get(count2test).getArtistName() + " - " + listenEvents.get(count2test).getSongName() + " - " + listenEvents.get(count2test).getSongCode()  + " - " + listenEvents.get(count2test).getYearListened()  + " - " + listenEvents.get(count2test).getMonthListened() + " - " + listenEvents.get(count2test).getDayListened() + " - " + listenEvents.get(count2test).getHourListened() + " - " + listenEvents.get(count2test).getMinuteListened());
                count2test++;
            }
            bufferedReader.close();


        }catch(IOException ioe) {
            ioe.printStackTrace();
         }

    }
}