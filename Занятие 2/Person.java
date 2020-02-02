package Занятие2;

public class Person extends LivingBeing implements Walk{
    public int age = 20;

    public boolean sleepEnough(double hoursOfSleep) {
        if (hoursOfSleep > 7) {
            return true;
        } else {
            return false;
        }
    }
    public boolean canWalk() {
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + age;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        return obj instanceof Person && age == ((Person) obj).age;
    }
    @Override
    public String toString() {
        return "Person at the age " + age;
    }
}
