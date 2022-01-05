package com.example.courseEnrollment.repository;

import com.example.courseEnrollment.dto.CourseEnrollmentDto;
import com.example.courseEnrollment.model.CourseEnrollment;
import com.example.courseEnrollment.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseEnrollmentRepository extends CrudRepository<CourseEnrollment, Integer> {
    @Query("SELECT c FROM CourseEnrollment c WHERE c.userId = ?1")
    List<CourseEnrollment> findByUId(Integer id);

    List<CourseEnrollment> findAllByUserId(Integer id);

    Optional<CourseEnrollment> findById(Integer id);
}
