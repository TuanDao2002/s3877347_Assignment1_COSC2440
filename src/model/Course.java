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

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", ID, name, credits);
    }
}
