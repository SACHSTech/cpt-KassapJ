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
    private int yearListened;
    private int monthListened;
    private int dayListened;
    private int hourListened;
    private int minuteListened;

    public Song(int intMsListened, String strArtistName, int intYear, int intMonth, int intDay, int intHours, int intMinute, String strSongName){
        this.artistName = strArtistName;
        this.songName = strSongName;
        this.msListened = intMsListened;
        this.yearListened = intYear;
        this.monthListened = intMonth;
        this.dayListened = intDay;
        this.hourListened = intHours;
        this.minuteListened = intMinute;
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

