package com.example.courseEnrollment.repository;

import com.example.courseEnrollment.model.Course;
import com.example.courseEnrollment.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query("SELECT c FROM Course c WHERE c.courseName = ?1")
    Course findByCourseName(String courseName);

    @Query("SELECT c FROM Course c WHERE c.id = ?1")
    Course findByCourseId(int id);
}
