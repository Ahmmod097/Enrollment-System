package com.example.courseEnrollment.aop;

import com.example.courseEnrollment.dto.UserDto;
import com.example.courseEnrollment.model.Course;
import com.example.courseEnrollment.model.CourseEnrollment;
import com.example.courseEnrollment.model.User;
import com.example.courseEnrollment.repository.CourseEnrollmentRepository;
import com.example.courseEnrollment.repository.CourseRepository;
import com.example.courseEnrollment.repository.UserRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class LoggingAspect {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;

    @Autowired
    public LoggingAspect(UserRepository userRepository, CourseRepository courseRepository, CourseEnrollmentRepository courseEnrollmentRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.courseEnrollmentRepository = courseEnrollmentRepository;
    }

    @Around("@annotation(Loggable)")
    public Object checkEmailAlreadyExist(ProceedingJoinPoint joinPoint) throws Throwable {
        UserDto userDto = (UserDto) joinPoint.getArgs()[0];
        User user = userRepository.findByEmail(userDto.getEmail());
        if(user==null){
            joinPoint.proceed();
            return "signup_process";
        }
        return "registration_form";

    }

    @Around("@annotation(Loggable1)")
    public Object checkEnrollmentAlreadyExist(ProceedingJoinPoint joinPoint) throws Throwable {
        String courseName = (String) joinPoint.getArgs()[0];
        Principal principal  = (Principal) joinPoint.getArgs()[1];
        Model model = (Model) joinPoint.getArgs()[2];

        Course course = courseRepository.findByCourseName(courseName);
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user.getId());
        Integer courseId = course.getId();
        Integer userId = user.getId();
        List<CourseEnrollment> courseEnrollment = (List<CourseEnrollment>)
                courseEnrollmentRepository.findAll();
        for(CourseEnrollment elem:courseEnrollment){
             if(Objects.equals(elem.getCourseId(), courseId) && Objects.equals(elem.getUserId(), userId)){
                 return "error_message";
             }
        }
        joinPoint.proceed();
        return "enrollment_message";
    }

    @Pointcut("within(com.example.courseEnrollment.UserController)")
    public void registerControllerMethod(){
    }
}
