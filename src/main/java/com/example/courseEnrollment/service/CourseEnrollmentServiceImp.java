package com.example.courseEnrollment.service;

import com.example.courseEnrollment.dto.CourseDto;
import com.example.courseEnrollment.dto.CourseEnrollmentDto;
import com.example.courseEnrollment.dto.UserDto;
import com.example.courseEnrollment.model.CourseEnrollment;
import com.example.courseEnrollment.repository.CourseEnrollmentRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Service
public class CourseEnrollmentServiceImp implements CourseEnrollmentService{
    private final CourseEnrollmentRepository courseEnrollmentRepository;

    @Autowired
    public CourseEnrollmentServiceImp(CourseEnrollmentRepository courseEnrollmentRepository) {
        this.courseEnrollmentRepository = courseEnrollmentRepository;
    }

    @Override
    public CourseEnrollmentDto addEnrollment(UserDto userDto, CourseDto courseDto){

        CourseEnrollment courseEnrollment = CourseEnrollment.builder()
                                                            .courseId(courseDto.getId())
                                                            .userId(userDto.getId())
                                                            .build();

        courseEnrollmentRepository.save(courseEnrollment);

        CourseEnrollmentDto courseEnrollmentDto = courseConvertToCourseEnrollmentDto(courseEnrollment);
        return courseEnrollmentDto;
    }

    @Override
    public List<CourseEnrollmentDto> getEnrollment(int userId) {

        List<CourseEnrollment> courseEnrollment = courseEnrollmentRepository.findByUId(userId);


        List<CourseEnrollmentDto>courseEnrollmentDtoList = new ArrayList<>();
        for(CourseEnrollment elem: courseEnrollment){
            CourseEnrollmentDto courseEnrollmentDto = new CourseEnrollmentDto();
            courseEnrollmentDto = courseConvertToCourseEnrollmentDto(elem);
            courseEnrollmentDtoList.add(courseEnrollmentDto);
        }

        return courseEnrollmentDtoList;
    }

    private CourseEnrollmentDto courseConvertToCourseEnrollmentDto(CourseEnrollment courseEnrollment) {
        CourseEnrollmentDto courseEnrollmentDto = new CourseEnrollmentDto();
        courseEnrollmentDto.setId(courseEnrollment.getId());
        courseEnrollmentDto.setCourseId(courseEnrollment.getCourseId());
        courseEnrollmentDto.setUserId(courseEnrollment.getUserId());
        return courseEnrollmentDto;
    }
}
