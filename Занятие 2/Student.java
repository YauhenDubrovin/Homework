package Занятие2;

public class Student extends Person {

    public String isGood (double averageScore) {
        if (averageScore >= 7) {
            return "Good student";
        } else {
            return "Not a good student";
        }
    }

    protected boolean getScholarship (double averageScore) {
        if (averageScore >= 4) {
            return true;
        } else {
            return false;
        }
    }

    private String hasFaceColor (boolean goodStudent) {
        if (goodStudent == true) {
            return "green";
        } else {
            return "normal";
        }
    }
    int hasYearsLeft (int yearsDone) {
        return 4-yearsDone;
    }
    public boolean sleepEnough(double hoursOfSleep) {
        if (hoursOfSleep > 6) {
            return true;
        } else {
            return false;
        }
    }
}
