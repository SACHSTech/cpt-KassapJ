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
            String tempMsListened = "";
            int msListened = 0;
            String dateListened = "";
            String tempString2 = "";
            int songCode = 0;
            int count = 0;
            int tempIndex = 0;

            while((line = bufferedReader.readLine()) != null){
                tempArray = line.split(delimiter);
                count = 0;
                // This isn't useful to the code, just to see what's happening. This is where we would access the variables and add it to the object
                for(String tempString : tempArray){
                    // Temp string is the data that I'm at this current moment
                    if(count == 0){
                        tempMsListened = tempString;
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
                        songCode *= 100;
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
                            songCode *= 100;
                        }
                    }
                }
                songName = tempString2;
                msListened = Integer.parseInt(tempMsListened);

                listenEvents.add(listeningEventBinarySearch(listenEvents, songCode), new ListenEvent(msListened, artistName, dateListened, songName, songCode));

                // Use binary search to sort these objects into the arraylist properly
                // SONGS OBJECT
               
                
                tempIndex = songsBinarySearch(songs, songCode, msListened);
                if(tempIndex >= 0){
                    songs.add(tempIndex, new Song(msListened, artistName, songName, songCode));
                }
                
                
                
            }
            bufferedReader.close();

            // Testing the arrays we made

            for(int i = 0; i < songs.size(); i++){
                System.out.println(songs.get(i).getArtistName() + " - " + songs.get(i).getSongName() + " - " + songs.get(i).getMsListened() + " - " + songs.get(i).getSongCode());
            }
        
            
            //for(int i = 0; i < listenEvents.size(); i++){
            //System.out.println(listenEvents.get(i).getMsListened() + " - " + listenEvents.get(i).getArtistName() + " - " + listenEvents.get(i).getSongName() + " - " + listenEvents.get(i).getSongCode()  + " - " + listenEvents.get(i).getYearListened()  + " - " + listenEvents.get(i).getMonthListened() + " - " + listenEvents.get(i).getDayListened() + " - " + listenEvents.get(i).getHourListened() + " - " + listenEvents.get(i).getMinuteListened());
            //}
            

        }catch(IOException ioe) {
            ioe.printStackTrace();
         }

    }

    /**
    * this method takes a key and array, and searches through the array (edited to work for the song and listenevent classes) and returns an int
    * representing the index where the key matches. If not found, it will return where it's supposed to be
    *
    * @param int[] array, int key
    * @return int[] indexes (2 indexes)
    */
    public int songsBinarySearch(ArrayList<Song> arr, int key, int msListened){

        int low = 0;
        int high = arr.size() - 1;

        while(low <= high){
            int mid = (low + high) / 2;
            int cur = arr.get(mid).getSongCode();
            if(cur == key){
                // Add the objects together
                arr.get(mid).addMsListened(msListened);

                // return -1 so we don't add it to the arraylist
                return -1;
            }
            else if(cur < key){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return high + 1;
    }

    /**
    * this method takes a key and array, and searches through the array (edited to work for the song and listenevent classes) and returns an int
    * representing the index where the key matches. If not found, it will return where it's supposed to be
    *
    * @param int[] array, int key
    * @return int[] indexes (2 indexes)
    */
    public int listeningEventBinarySearch(ArrayList<ListenEvent> arr, int key){

        int low = 0;
        int high = arr.size() - 1;

        while(low <= high){
            int mid = (low + high) / 2;
            int cur = arr.get(mid).getSongCode();
            if(cur == key){
                return mid;
            }
            else if(cur < key){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return high + 1;
    }

    /**
    * this method will get the name of a song in the songs object array, given an index variable
    *
    * @param int x (index)
    * @return String songName
    */
    public int getSongsSize(){
        return this.songs.size();
    }

    /**
    * this method will get the name of a song in the songs object array, given an index variable
    *
    * @param int x (index)
    * @return String songName
    */
    public String getSongName(int x){
        return this.songs.get(x).getSongName();
    }

    /**
    * this method will get the msListened of a song
    *
    * @param int x (index)
    * @return String songName
    */
    public int getSongsMsListened(int x){
        return this.songs.get(x).getMsListened();
    }
}
