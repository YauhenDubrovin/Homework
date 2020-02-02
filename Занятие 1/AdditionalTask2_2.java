package Занятие1;

import java.util.Random;

public class AdditionalTask2_2 {

    public static int ARRAY_LENGTH = 10000;
    private static int[] arr = new int[ARRAY_LENGTH];
    private static Random generator = new Random();

    public static void initArray() {
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            arr[i] = generator.nextInt(100000);
        }
    }

    public static void printArray() {
        for (int i = 0; i < ARRAY_LENGTH - 1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void sort(int[] arr) {
        for (int inc = arr.length / 2; inc >= 1; inc = inc / 2)
            for (int step = 0; step < inc; step++)
                insertionSort(arr, step, inc);
    }

    private static void insertionSort(int[] arr, int start, int inc) {
        int tmp;
        for (int i = start; i < arr.length - 1; i += inc)
            for (int j = Math.min(i + inc, arr.length - 1); j - inc >= 0; j = j - inc)
                if (arr[j - inc] > arr[j]) {
                    tmp = arr[j];
                    arr[j] = arr[j - inc];
                    arr[j - inc] = tmp;
                } else break;
    }

    public static void main(String[] args) {
        initArray();
        printArray();
        sort(arr);
        printArray();
    }
}