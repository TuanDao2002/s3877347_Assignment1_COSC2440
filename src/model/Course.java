package model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return credits == course.credits && ID.equals(course.ID) && name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, credits);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", ID, name, credits);
    }
}
