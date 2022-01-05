package com.example.courseEnrollment.dto;

import com.example.courseEnrollment.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Integer id;
    private String courseName;
    private String courseContent;
    private String instructorName;

}
