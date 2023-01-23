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
                for(int i = 0; i < songName.length(); i++){
                    char ch = songName.charAt(i);
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
                songName = tempString2;
                tempString2 = "";
                for(int i = 0; i < artistName.length(); i++){
                    char ch = artistName.charAt(i);
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
                artistName = tempString2;
                msListened = Integer.parseInt(tempMsListened);

                listenEvents.add(Sorting.listenEventBinarySearch(listenEvents, songCode), new ListenEvent(msListened, artistName, dateListened, songName, songCode));

                // Use binary search to sort these objects into the arraylist properly
                // SONGS OBJECT
               
                
                tempIndex = Sorting.songsBinarySearchSort(songs, songCode, msListened);
                if(tempIndex >= 0){
                    songs.add(tempIndex, new Song(msListened, artistName, songName, songCode));
                }
                
                
                
            }
            bufferedReader.close();

            // Testing the arrays we made

            //for(int i = 0; i < songs.size(); i++){
            //    System.out.println(songs.get(i).getArtistName() + " - " + songs.get(i).getSongName() + " - " + songs.get(i).getMsListened() + " - " + songs.get(i).getSongCode());
            //}
            //
            //for(int i = 0; i < listenEvents.size(); i++){
            //System.out.println(listenEvents.get(i).getMsListened() + " - " + listenEvents.get(i).getArtistName() + " - " + listenEvents.get(i).getSongName() + " - " + listenEvents.get(i).getSongCode()  + " - " + listenEvents.get(i).getYearListened()  + " - " + listenEvents.get(i).getMonthListened() + " - " + listenEvents.get(i).getDayListened() + " - " + listenEvents.get(i).getHourListened() + " - " + listenEvents.get(i).getMinuteListened());
            //}
            

        }catch(IOException ioe) {
            ioe.printStackTrace();
         }

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

    public ArrayList<Song> getSongs(){
        return this.songs;
    }

    public ArrayList<ListenEvent> getListenEvents(){
        return this.listenEvents;
    }

    public Song getSong(int x){
        return this.songs.get(x);
    }

    public ArrayList<Song> sortSongsMsListened(ArrayList<Song> x){
        int[] tempArr = new int[x.size()];
        ArrayList <Song> tempSongs;
        tempSongs = new ArrayList<Song>();

        for(int i = 0; i < x.size() - 1; i++){
            tempArr[i] = x.get(i).getMsListened();
        }

        Sorting.mergeSort(tempArr);

        for(int i = 0; i < x.size() - 1; i++){
            int index = Sorting.songsBinarySearch(x, tempArr[i]);
            if(index >= 0){
                x.remove(index);
                tempSongs.add(0, new Song(tempArr[i], 
                x.get(index).getArtistName(),
                x.get(index).getSongName(),
                x.get(index).getSongCode()));
            }
        }

        return tempSongs;
    }
}
