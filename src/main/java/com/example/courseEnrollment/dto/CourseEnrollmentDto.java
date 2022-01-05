package com.example.courseEnrollment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEnrollmentDto {
    private Integer id;
    private Integer userId;
    private Integer courseId;
}
