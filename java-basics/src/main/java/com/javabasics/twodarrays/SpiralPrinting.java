package com.javabasics.twodarrays;

public class SpiralPrinting {

    public static void main(String[] args) {
        int N = 4;
        int sequence = 0;
        int[][] mat = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j= 0; j< N; j++) {
                mat[i][j] = ++sequence;
            }
        }
//        System.out.println("Full matrix ::");
//        for(int i = 0; i < N; i++) {
//            for(int j= 0; j< N; j++) {
//                System.out.print(mat[i][j]);
//            }
//            System.out.println();
//        }
       printSpiral(mat, N);
    }
    public static void printSpiral(int[][] mat, int N) {

        int start = 0, end = N - 1; // end = 5 -1  = 4 in 1st iteration
        int row = 0, col = 0;
        while(end >= (N/2)) {
            while(col < end) {
                System.out.print(mat[row][col] + " "); col++;
            }
            while(row < end) {
                System.out.print(mat[row][col] + " "); row++;
            }
            while(col > start) {
                System.out.print(mat[row][col] + " "); col--;
            }
            while(row > start) {
                System.out.print(mat[row][col] + " ");
                row--;
            }
            row++;col++;
            if(N % 2 != 0 && ((end - 1) == N/2)) {
                System.out.println(mat[row][col]);
                break;
            }

            end = end - 1;
            start = row;
        }
    }
}
