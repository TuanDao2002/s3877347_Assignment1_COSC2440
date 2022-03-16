package model;

import java.util.Date;

public class Student {
    private String ID;
    private String name;
    private Date birthdate;

    public Student(String studentID, String name, Date birthdate) {
        this.ID = studentID;
        this.name = name;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", ID, name, birthdate == null ? "########" : birthdate);
    }
}
