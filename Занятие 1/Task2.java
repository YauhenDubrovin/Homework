package Занятие1;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        System.out.println("Введите количество элементов массива:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        int result = 1;

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random()*20) + 1;
            System.out.print(array[i] + " ");
            result = result*array[i];
        }
        System.out.println();
        System.out.println(result);
    }
}
