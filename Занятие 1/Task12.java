package Занятие1;

import java.util.Scanner;

public class Task12 {
    public static void main(String[] args) {
        System.out.println("Введите количество элементов массива:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        int[] tempArray = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 20);
            tempArray[i] = array[i];
            System.out.print(array[i] + "  ");
        }
        System.out.println();

        for (int i = 0; i < array.length - 2; i++) {
            array[i+2] = tempArray[i];
        }
        array[0] = 0;
        array[1] = 0;

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
    }
}
