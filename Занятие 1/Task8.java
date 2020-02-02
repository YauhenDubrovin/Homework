package Занятие1;

import java.util.Scanner;

public class Task8 {
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

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        System.out.println("Максимальный элемент в массиве: " + max);
        System.out.println("Минимальный элемент в массиве: " + min);
    }
}
