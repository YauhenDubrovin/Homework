package Занятие1;

import java.util.Scanner;

public class Task7 {
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

        int j = 0;
        int temp;
        while (j < array.length/2) {
            temp = array[j];
            array[j] = array[array.length - j - 1];
            array[array.length - j - 1] = temp;
            j++;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }
}
