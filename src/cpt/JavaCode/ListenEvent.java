package cpt.JavaCode;

public class ListenEvent {
    private String artistName;
    private String songName;
    private String dateListened;
    private int msListened;
    private int yearListened;
    private int monthListened;
    private int dayListened;
    private int hourListened;
    private int minuteListened;
    private String combinedNames;

    public ListenEvent(int intMsListened, String strArtistName, String strDate, String strSongName, String strCombinedNames){
        this.artistName = strArtistName;
        this.songName = strSongName;
        this.msListened = intMsListened;
        this.dateListened = strDate;
        this.combinedNames = strCombinedNames;

        //CONVERTING THE DATE AND TIMES TO THEIR OWN INT VARIABLES
        for(int i = 0; i < dateListened.length(); i++){
            char ch = dateListened.charAt(i);
            if(i <= 3){
                this.yearListened += (ch - '0');
                if(i < 3){
                    this.yearListened *= 10;
                }
            }
            else if(i > 4 && i < 7){
                this.monthListened += (ch - '0');
                if(i < 6){
                    this.monthListened *= 10;
                }
            }
            else if(i > 7 && i < 10){
                this.dayListened += (ch - '0');
                if(i < 9){
                    this.dayListened *= 10;
                }
            }
            else if(i > 10 && i < 13){
                this.hourListened += (ch - '0');
                if(i < 12){
                    this.hourListened *= 10;
                }
            }
            else if(i > 13){
                this.minuteListened += (ch - '0');
                if(i < 15){
                    this.minuteListened *= 10;
                }
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

    /**
    * Method that gets the String value of the date
    *
    * @param N/A
    * @return int strDate
    */
    public String getStrDate(){
        return this.dateListened;
    }
}
