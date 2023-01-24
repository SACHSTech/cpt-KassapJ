package cpt.JavaCode;

import java.util.ArrayList;

public class Sorting {
    /**
    * this method takes an array, key and msListened, and sorts the songs given to only have one song each,
    * collecting all of their mslistened, and sorting them alphabetically
    *
    * @param ArrayList<Song> arr, String key, int msListened
    * @return int key
    */
    public static int songsBinarySearchSort(ArrayList<Song> arr, String key, int msListened){
        // Remove any unknown tracks
        if(key.equals("Unknown TrackUnknown Artist")){
            return -1;
        }
        
        int low = 0;
        int high = arr.size() - 1;

        while(low <= high){
            int mid = (low + high) / 2;
            String cur = (arr.get(mid).getSongName() + arr.get(mid).getArtistName());

            if(cur.equals(key)){
                // Add the objects together
                arr.get(mid).addMsListened(msListened);

                // return -1 so we don't add it to the arraylist
                return -1;
            }
            else{
                // Comparing strings
                if(key.length() <= cur.length()){
                    for(int i = 0; i < key.length(); i++){
                        char ch1 = Character.toUpperCase(cur.charAt(i));
                        char ch2 = Character.toUpperCase(key.charAt(i));
                        if(ch2 > ch1){
                            low = mid +1;
                            break;
                        }
                        else if(ch1 > ch2){
                            high = mid -1;
                            break;
                        }
                        else if(i == key.length() - 1){
                            // Add the objects together
                            arr.get(mid).addMsListened(msListened);

                            // return -1 so we don't add it to the arraylist
                            return -1;
                        }
                    }
                }
                else if(cur.length() < key.length()){
                    for(int i = 0; i < cur.length(); i++){
                        char ch1 = Character.toUpperCase(cur.charAt(i));
                        char ch2 = Character.toUpperCase(key.charAt(i));
                        if(ch1 < ch2){
                            low = mid +1;
                            break;
                        }
                        else if(ch1 > ch2){
                            high = mid -1;
                            break;
                        }
                        else if(i == cur.length() - 1){
                            // Add the objects together
                            arr.get(mid).addMsListened(msListened);

                            // return -1 so we don't add it to the arraylist
                            return -1;
                        }
                    }
                }
            }
            
        }
        return high + 1;
    }

    /**
    * this method takes an array, key and msListened, and sorts the songs given to only have one song each,
    * collecting all of their mslistened, and sorting them alphabetically
    *
    * @param ArrayList<Song> arr, String key, int msListened
    * @return int key
    */
    public static int listenEventBinarySearchSort(ArrayList<ListenEvent> arr, String key){
        // Remove any unknown tracks
        if(key.equals("Unknown TrackUnknown Artist")){
            return -1;
        }

        int low = 0;
        int high = arr.size() - 1;

        while(low <= high){
            int mid = (low + high) / 2;
            String cur = (arr.get(mid).getSongName() + arr.get(mid).getArtistName());

            if(cur.equals(key)){
                return mid;
            }

            // Comparing strings
            if(key.length() <= cur.length()){
                for(int i = 0; i < key.length(); i++){
                    char ch1 = Character.toUpperCase(cur.charAt(i));
                    char ch2 = Character.toUpperCase(key.charAt(i));
                    if(ch2 > ch1){
                        low = mid +1;
                        break;
                    }
                    else if(ch1 > ch2){
                        high = mid -1;
                        break;
                    }
                    else if(i == key.length() - 1){
                        // return -1 so we don't add it to the arraylist
                        return -1;
                    }
                }
            }
            else if(cur.length() < key.length()){
                for(int i = 0; i < cur.length(); i++){
                    char ch1 = Character.toUpperCase(cur.charAt(i));
                    char ch2 = Character.toUpperCase(key.charAt(i));
                    if(ch1 < ch2){
                        low = mid +1;
                        break;
                    }
                    else if(ch1 > ch2){
                        high = mid -1;
                        break;
                    }
                    else if(i == cur.length() - 1){
                        // return -1 so we don't add it to the arraylist
                        return -1;
                    }
                }
            }
        }
        return high + 1;
    }

    /**
    * this method takes a key and array, and searches through the array (edited to work for the song and listenevent classes) and returns an int
    * representing the index where the key matches. If not found, it will return where it's supposed to be
    *
    * @param int[] array, int key
    * @return int index
    */
    public static int songsLinearSearch(ArrayList<Song> arr, int key){

        int low = 0;
        int high = arr.size() - 1;

        for(int i = 0; i < arr.size(); i++){
            int element = arr.get(i).getMsListened();
            if(element == key){
                return i;
            }
        }
        return -1;
    }

    /**
    * this method takes an array, key and msListened, and sorts the songs given to only have one song each,
    * collecting all of their mslistened, and sorting them alphabetically
    *
    * @param ArrayList<Song> arr, String key, int msListened
    * @return int key
    */
    public static int songsBinarySearch(ArrayList<Song> arr, String key, ArrayList<Integer> restrictedIndex){        
        int low = 0;
        int high = arr.size() - 1;
        boolean indexValid = false;
        while(low <= high){
            int mid = (low + high) / 2;
            String cur = (arr.get(mid).getSongName() + arr.get(mid).getArtistName());

            if(cur.equals(key)){
                // If most of the characters match, assume they're the same song
                // Make sure that we're returning duplicate index's
                for(int j = 0; j < restrictedIndex.size(); j++){
                    if(mid == restrictedIndex.get(j)){
                        indexValid = false;
                        break;
                    }
                    else{
                        indexValid = true;
                    }
                }
                if(indexValid){
                    return mid;
                }
            }
            else{
                // Comparing strings
                if(key.length() <= cur.length()){
                    for(int i = 0; i < key.length(); i++){
                        char ch1 = Character.toUpperCase(cur.charAt(i));
                        char ch2 = Character.toUpperCase(key.charAt(i));
                        if(ch2 > ch1){
                            low = mid +1;
                            break;
                        }
                        else if(ch1 > ch2){
                            high = mid -1;
                            break;
                        }
                        else if(i == key.length() - 1){
                            // If most of the characters match, assume they're the same song
                            // Make sure that we're returning duplicate index's
                            for(int j = 0; j < restrictedIndex.size(); j++){
                                if(mid == restrictedIndex.get(j)){
                                    indexValid = false;
                                    break;
                                }
                                else{
                                    indexValid = true;
                                }
                            }
                            if(indexValid){
                                return mid;
                            }
                        }
                    }
                }
                else if(cur.length() < key.length()){
                    for(int i = 0; i < cur.length(); i++){
                        char ch1 = Character.toUpperCase(cur.charAt(i));
                        char ch2 = Character.toUpperCase(key.charAt(i));
                        if(ch1 < ch2){
                            low = mid +1;
                            break;
                        }
                        else if(ch1 > ch2){
                            high = mid -1;
                            break;
                        }
                        else if(i == cur.length() - 1){
                            // If most of the characters match, assume they're the same song
                            // Make sure that we're returning duplicate index's
                            for(int j = 0; j < restrictedIndex.size(); j++){
                                if(mid == restrictedIndex.get(j)){
                                    indexValid = false;
                                    break;
                                }
                                else{
                                    indexValid = true;
                                }
                            }
                            if(indexValid){
                                return mid;
                            }
                        }
                    }
                }
            }
            
        }
        // Not in Arraylist
        return -1;
    }
    


    public static void mergeSort(int[] arr){
        int[] temp = new int[arr.length];
        mergeSortHelper(arr, 0, arr.length - 1, temp);
    }

    public static void mergeSortHelper(int[] arr, int from, int to, int[] temp){
        //If the array length is greater than 1

        if(to - from >= 1){
            int mid = (from + to) / 2;
            mergeSortHelper(arr, from, mid, temp);
            mergeSortHelper(arr, mid + 1, to, temp);
            merge(arr, from, mid, to, temp);
        }
    }

    public static void merge(int[] arr, int from, int mid, int to, int[] temp){
        int i = from;
        int j = mid + 1;
        int k = from;

        while(i <= mid && j <= to){
            if(arr[i] < arr[j]){
                temp[k] = arr[i];
                i++;
            }
            else{
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        
        while(i <= mid){
            temp[k] = arr[i];
            i++;
            k++;
        }

        while(j <= to){
            temp[k] = arr[j];
            j++;
            k++;
        }

        for(k = from; k <= to; k++){
            arr[k] = temp[k];
        }
    }
}
