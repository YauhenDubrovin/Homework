package Занятие1;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        System.out.println("Введите количество элементов массива:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        String result = "";

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 20);
            System.out.print(array[i] + " ");
            if (array[i] == 0) {
                result = result + i + " ";
            }
        }
        System.out.println();
        if (result.length() != 0) {
            System.out.println("Индексы нулевых элементов: " + result);
        } else {
            System.out.println("В массиве нет нулевых элементов");
        }
    }
}
