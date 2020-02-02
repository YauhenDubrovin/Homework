package Занятие1;

import java.util.Scanner;

public class Task9 {
    public static void main(String[] args) {
        System.out.println("Введите количество элементов массива:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 20);
            System.out.print(array[i] + "  ");
        }
        System.out.println();

        int max = array[0];
        int min = array[0];
        int indexMax = 0;
        int indexMin = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                indexMax = i;
            }
            if (array[i] < min) {
                min = array[i];
                indexMin = i;
            }
        }
        System.out.println("Индекс максимального элемента в массиве: " + indexMax);
        System.out.println("Индекс минимального элемента в массиве: " + indexMin);
    }
}
