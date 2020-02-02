package Занятие1;

import java.util.Scanner;

public class Task10 {
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

        boolean flag = true;

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i+1]) {
                flag = false;
            }
        }
        if (flag == true) {
            System.out.println("Массив является возрастающей последовательностью");
        } else {
            System.out.println("Массив не является возрастающей последовательностью");
        }
    }
}
