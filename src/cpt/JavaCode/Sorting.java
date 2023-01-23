package cpt.JavaCode;

import java.util.ArrayList;

public class Sorting {
    /**
    * this method takes a key and array, and searches through the array (edited to work for the song and listenevent classes) and returns an int
    * representing the index where the key matches. If not found, it will return where it's supposed to be
    *
    * @param int[] array, int key
    * @return int[] indexes (2 indexes)
    */
    public static int listenEventBinarySearch(ArrayList<ListenEvent> arr, int key){

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
    * this method takes a key and array, and searches through the array (edited to work for the song and listenevent classes) and returns an int
    * representing the index where the key matches. If not found, it will return where it's supposed to be
    *
    * @param int[] array, int key
    * @return int index
    */
    public static int songsBinarySearch(ArrayList<Song> arr, int key){

        int low = 0;
        int high = arr.size() - 1;

        while(low <= high){
            int mid = (low + high) / 2;
            int cur = arr.get(mid).getMsListened();

            if(cur == key){
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
    public static int songsBinarySearchSort(ArrayList<Song> arr, int key, int msListened){

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
