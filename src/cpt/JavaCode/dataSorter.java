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
                
                // Fix artist name and song name having € in it, and convert those to commas
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
                }
                artistName = tempString2;
                msListened = Integer.parseInt(tempMsListened);

                tempIndex = Sorting.listenEventBinarySearchSort(listenEvents, (songName + artistName));
                if( tempIndex == -1){

                }
                else{
                    listenEvents.add(tempIndex, new ListenEvent(msListened, artistName, dateListened, songName, (artistName + songName)));
                }
                

                // Use binary search to sort these objects into the arraylist properly
                // SONGS OBJECT
               
                
                tempIndex = Sorting.songsBinarySearchSort(songs, (songName + artistName), msListened);
                if(tempIndex >= 0){
                    songs.add(tempIndex, new Song(msListened, artistName, songName, (songName + artistName)));
                }
                
                
                
            }
            bufferedReader.close();
            

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

        for(int i = 0; i < x.size(); i++){
            tempArr[i] = x.get(i).getMsListened();
        }

        Sorting.mergeSort(tempArr);

        for(int i = 0; i < x.size(); i++){
            int index = Sorting.songsLinearSearch(x, tempArr[i]);

            if(index >= 0){
                tempSongs.add(0, new Song(x.get(index).getMsListened(), 
                x.get(index).getArtistName(),
                x.get(index).getSongName(),
                x.get(index).getCombinedNames()));
                //x.get(index).setMsListened(0);
            }
        }

        return tempSongs;
    }
}
