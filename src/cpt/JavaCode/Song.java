package cpt.JavaCode;
/*
 * An object containing important variable of a song's listening data
 * @author John Matthew Kassapian
 */
public class Song {
    private String artistName;
    private String songName;
    private int songCode;
    private int msListened;

    public Song(String msListened, String strArtistName, String strSongName, int intSongCode){
        this.artistName = strArtistName;
        this.songName = strSongName;
        this.msListened = Integer.parseInt(msListened);
        this.songCode = intSongCode;

        // Converting the string date to individual integers

        // Creating a song code with the first bits of the artist name and song name
    }
    /**
    * this method returns a String value that represents the name of the artist
    *
    * @param N/A
    * @return String artistName
    */
    public String getArtistName(){
        return this.artistName;
    }

}

