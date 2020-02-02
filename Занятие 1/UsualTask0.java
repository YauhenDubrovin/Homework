package Занятие1;

public class UsualTask0 {
    public static void main(String[] args) {
        int i = 4;
        System.out.print(i++ + " ");
        System.out.print(i + " ");
    //  при i++ используется действующее значение переменной, единица добавляется после
    //  использования

        i = 5;
        System.out.print(++i);
    // при ++i сразу идет добавление единицы и сразу используется значение, увеличенное
    // на единицу
    }
}
