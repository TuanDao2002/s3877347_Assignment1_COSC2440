package model;

import utility.DateConverter;

import java.util.Date;
import java.util.Objects;

public class Student {
    private String ID;
    private String name;
    private Date birthdate;

    public Student(String studentID, String name, Date birthdate) {
        this.ID = studentID;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return ID.equals(student.ID) && name.equals(student.name) && birthdate.equals(student.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, birthdate);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", ID, name, birthdate == null ? "unknown" : DateConverter.dateToString(birthdate));
    }
}
