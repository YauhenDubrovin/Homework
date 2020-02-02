package Занятие2;

public class Methods {

    public int min(int a, int b) {
        if (a<b) {
            return a;
        } else {
            return b;
        }
    }

    public boolean even(int a) {
        if (a%2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int square(int a) {
        return a*a;
    }

    public int cube(int a) {
        return a*a*a;
    }

    public static void main(String[] args) {
        Methods methods = new Methods();
        System.out.println(methods.min(3,5));
        System.out.println(methods.even(2));
        System.out.println(methods.square(5));
        System.out.println(methods.cube(3));
    }
}
