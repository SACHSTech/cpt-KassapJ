package cpt.JavaCode;

public class ListenEvent {
    private String artistName;
    private String songName;
    private String dateListened;
    private int songCode;
    private int msListened;
    private int yearListened;
    private int monthListened;
    private int dayListened;
    private int hourListened;
    private int minuteListened;

    public ListenEvent(String msListened, String strArtistName, String strDate, String strSongName){
        this.artistName = strArtistName;
        this.songName = strSongName;
        this.msListened = Integer.parseInt(msListened);
        this.songCode = 00000000;
        this.dateListened = strDate;

        //2022-01-11 00:03 example of date and time
        for(int i = 0; i < dateListened.length() - 1; i++){
            char ch = dateListened.charAt(i);
            if(i <= 3){
                this.yearListened += ch;
            }
            else if(i >= 5 || i <= 6){
                this.monthListened += ch;
            }
            else if(i >= 8 || i <= 9){
                this.dayListened += ch;
            }
            else if(i >= 11 || i <= 12){
                this.hourListened += ch;
            }
            else if(i >= 11 || i <= 12){
                this.minuteListened += ch;
            }
        }
    }

    /**
    * Method that gets the value represents the MsListened of this event
    *
    * @param N/A
    * @return int MsListened
    */
    public int getMsListened(){
        return this.msListened;
    }

    /**
    * Method that gets the value that represents the artists name
    *
    * @param N/A
    * @return String artistName
    */
    public String getArtistName(){
        return this.artistName;
    }

    /**
    * Method that gets the value that represents the name of the song
    *
    * @param N/A
    * @return String songName
    */
    public String getSongName(){
        return this.songName;
    }

    /**
    * Method that gets the value that represents the "code" used to sort the song
    *
    * @param N/A
    * @return String songCode
    */
    public int getSongCode(){
        return this.songCode;
    }

    /**
    * Method that gets the value that represents the string of the date listened
    *
    * @param N/A
    * @return String dateListened
    */
    public String getDateListened(){
        return this.dateListened;
    }

    /**
    * Method that gets the value that represents the year this listening event occured
    *
    * @param N/A
    * @return int yearListened
    */
    public int getYearListened(){
        return this.yearListened;
    }

    /**
    * Method that gets the value that represents the month this listening event occured
    *
    * @param N/A
    * @return int monthListened
    */
    public int getMonthListened(){
        return this.monthListened;
    }

    /**
    * Method that gets the value that represents the day this listening event occured
    *
    * @param N/A
    * @return int dayListened
    */
    public int getDayListened(){
        return this.dayListened;
    }

    /**
    * Method that gets the value that represents the hour this listening event occured
    *
    * @param N/A
    * @return int hourListened
    */
    public int getHourListened(){
        return this.hourListened;
    }
    
    /**
    * Method that gets the value that represents the minute this listening event occured
    *
    * @param N/A
    * @return int minuteListened
    */
    public int getMinuteListened(){
        return this.minuteListened;
    }
}
