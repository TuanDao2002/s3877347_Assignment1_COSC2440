package model;

public class Enrollment {
    private Student student;
    private Course course;
    private String semester;

    public Enrollment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return student.toString() + "," + course.toString() + "," + semester;
    }
}
