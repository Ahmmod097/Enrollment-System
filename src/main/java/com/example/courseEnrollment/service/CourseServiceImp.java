package com.example.courseEnrollment.service;

import com.example.courseEnrollment.dto.CourseDto;
import com.example.courseEnrollment.model.Course;
import com.example.courseEnrollment.model.User;
import com.example.courseEnrollment.repository.CourseRepository;
import com.example.courseEnrollment.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseServiceImp implements CourseService{
    private final CourseRepository courseRepository;


    @Autowired
    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDto addCourse(@NotNull CourseDto courseDto) {
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setCourseName(courseDto.getCourseName());
        course.setCourseContent(courseDto.getCourseContent());
        course.setInstructorName(courseDto.getInstructorName());
        courseRepository.save(course);
        return courseDto;
    }

    @Override
    public CourseDto findCourse(String course_name) {
        Course course = courseRepository.findByCourseName(course_name);
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setCourseContent(course.getCourseContent());
        courseDto.setInstructorName(course.getInstructorName());
        return courseDto;
    }

    @Override
    public CourseDto getCourse(int id) {

        Course course = courseRepository.findByCourseId(id);
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setCourseContent(course.getCourseContent());
        courseDto.setInstructorName(course.getInstructorName());
        return courseDto;
    }
}
