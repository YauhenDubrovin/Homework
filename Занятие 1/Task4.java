package Занятие1;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("Введите количество элементов массива:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 20);
            System.out.print(array[i] + " ");
            if (array[i] == 0) {
                sum = sum + 1;
            }
        }
        System.out.println();

        if (sum != 0) {
            System.out.println("Количество нулевых элементов: " + sum);
        } else {
            System.out.println("В массиве нет нулевых элементов");
        }
    }
}
