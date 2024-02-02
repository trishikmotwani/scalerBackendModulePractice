package com.javabasics.sorting.insertionsort;

import java.util.Arrays;

public class InsertionSortMain {

    public static void main(String[] args) {
        int[] arr = { 35, 1, 5, 16, 3, 9, 12};

        insertionSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {


        for(int i = 1; i< arr.length; i++) {

            int j = i;

            while(j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j -1);
                j--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
