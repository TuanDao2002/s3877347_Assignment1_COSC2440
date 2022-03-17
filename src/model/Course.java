package model;

public class Course {
    private String ID;
    private String name;
    private int credits;

    public Course(String ID, String name, int credits) {
        this.ID = ID;
        this.name = name;
        this.credits = credits;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", ID, name, credits);
    }
}
