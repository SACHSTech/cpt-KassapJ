package cpt.JavaCode;

public class Sorting {
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
