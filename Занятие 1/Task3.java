package Занятие1;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        System.out.println("Введите количество элементов массива:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 20) + 1;
            System.out.print(array[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            if ((i + 1)%3 == 0) {
                array[i] = array[i]*2;
            }
            System.out.print(array[i] + " ");
        }
        System.out.println();
        for (int i = 2; i < array.length; i = i + 3) {
            array[i] = array[i]*2;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}