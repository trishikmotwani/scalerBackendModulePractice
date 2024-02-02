package com.javabasics.sorting.selectionsort;

import java.util.Arrays;

public class SelectionSortMain {

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};

        selectionSort(arr);

        System.out.println(Arrays.toString(arr));
    }
    public static void selectionSort(int[] arr) {
        int noOfSwaps = 0;
        for(int i = 0; i < arr.length -1; i++) {

            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] > arr[j]) {
                    swap(arr, i, j);
                    noOfSwaps++;
                }
            }

            if(noOfSwaps == 0) break;
        }
        System.out.println("noOfSwaps ::" + noOfSwaps);
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
