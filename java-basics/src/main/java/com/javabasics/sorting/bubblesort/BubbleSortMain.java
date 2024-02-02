package com.javabasics.sorting.bubblesort;

public class BubbleSortMain {

    public static void main(String[] args) {

        int arr[] = { 1 , 2, 4 ,3, 5};
        // int arr[] = { 1, 2, 3, 4, 5};
        bubbleSort(arr);
        for(int item: arr) {
            System.out.println(item);
        }
    }

    public static void bubbleSort(int arr[]) {
        int noOfSwaps = 0;
        for(int i = 0; i < arr.length; i++) {

            for(int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j +1]) {
                    swap(arr, j, j + 1);
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
