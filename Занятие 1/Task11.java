package Занятие1;

import java.util.Scanner;

public class Task11 {
    public static void main(String[] args) {
        System.out.println("Введите количество элементов массива:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        double[] array = new double[size];
        double[] tempArray = new double[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 20);
            tempArray[i] = array[i];
            System.out.print(array[i] + "  ");
        }
        System.out.println();

        array[0] = array[1]/2;
        array[array.length-1] = array[array.length-2]/2;

        int j = 1;
        while (j < array.length - 1) {
            array [j] = (tempArray[j-1] + tempArray[j+1])/2;
            j++;
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
