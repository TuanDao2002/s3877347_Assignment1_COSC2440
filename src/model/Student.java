package model;

import utility.DateConverter;

import java.util.Date;

public class Student {
    private String studentID;
    private String name;
    private Date birthdate;

    public Student(String studentID, String name, Date birthdate) {
        this.studentID = studentID;
        this.name = name;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

    public static void main(String[] args) {
        String str = "02/30/2022";
        Date date = null;
        try {
            date = DateConverter.stringToDate(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Student student = new Student("s3877347","Tuan", date);
        System.out.println(student.toString());
    }
}
