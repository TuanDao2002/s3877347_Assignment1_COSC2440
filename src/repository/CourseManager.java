package repository;

import model.Course;

public interface CourseManager {
    Course getCourseById(String courseID);
}
