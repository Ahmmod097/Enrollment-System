package com.example.courseEnrollment.service;

import com.example.courseEnrollment.dto.CourseDto;
import com.example.courseEnrollment.dto.CourseEnrollmentDto;
import com.example.courseEnrollment.dto.UserDto;
import com.example.courseEnrollment.model.CourseEnrollment;

import java.util.List;

public interface CourseEnrollmentService {
    CourseEnrollmentDto addEnrollment(UserDto userDto, CourseDto courseDto);

    List<CourseEnrollmentDto> getEnrollment(int userId);
}
