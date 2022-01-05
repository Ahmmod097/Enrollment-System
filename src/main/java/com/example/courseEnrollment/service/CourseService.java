package com.example.courseEnrollment.service;

import com.example.courseEnrollment.dto.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDto addCourse(CourseDto courseDto);
    CourseDto findCourse(String courseName);
    CourseDto getCourse(int id);
}
