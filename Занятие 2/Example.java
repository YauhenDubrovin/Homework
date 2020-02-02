package Занятие2;

public class Example {
    public static void main(String[] args) {
        double averageScore = 6.9;
        int yearsDone = 2;
        Student student = new Student();
        System.out.println(student.getScholarship(averageScore));
        System.out.println(student.hasYearsLeft(yearsDone));
        System.out.println(student.isGood(averageScore));
        student.age = 20;

        Person person = new Person();

        double hoursOfSleep = 6.5;
        System.out.println(person.sleepEnough(hoursOfSleep));
        System.out.println(student.sleepEnough(hoursOfSleep));

        System.out.println(student.canWalk());

        System.out.println("Hashcode " + person.hashCode());
        System.out.println(person.toString());

        Person person2 = new Person();
        System.out.println(person.equals(person2));
    }
}
