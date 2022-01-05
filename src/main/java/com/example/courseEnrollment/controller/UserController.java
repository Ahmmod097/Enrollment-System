package com.example.courseEnrollment.controller;

import com.example.courseEnrollment.aop.Loggable1;
import com.example.courseEnrollment.dto.CourseDto;
import com.example.courseEnrollment.dto.CourseEnrollmentDto;
import com.example.courseEnrollment.dto.UserDto;
import com.example.courseEnrollment.service.CourseEnrollmentServiceImp;
import com.example.courseEnrollment.service.CourseServiceImp;
import com.example.courseEnrollment.service.UserAuthenticationService;
import com.example.courseEnrollment.validator.UserValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller

public class UserController {
    private final UserAuthenticationService userAuthenticationService;
    private final CourseServiceImp courseServiceImp;
    private final CourseEnrollmentServiceImp courseEnrollmentServiceImp;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserAuthenticationService userAuthenticationService, CourseServiceImp courseServiceImp,
                          CourseEnrollmentServiceImp courseEnrollmentServiceImp, UserValidator userValidator) {
        this.userAuthenticationService = userAuthenticationService;
        this.courseServiceImp = courseServiceImp;
        this.courseEnrollmentServiceImp = courseEnrollmentServiceImp;
        this.userValidator = userValidator;
    }



    @GetMapping("/")
    public String viewAuthenticatedHomePage(@NotNull Principal principal, @NotNull Model model){

        try {
            String name = principal.getName();
            UserDto userDto = userAuthenticationService.getUser(name);
            model.addAttribute("user", userDto.getId());
            return "home";
        }catch(NullPointerException e){
            return "index";
        }

    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model){
        model.addAttribute("userDto", new UserDto());
        return "registration_form";
    }


    @PostMapping("/signup")
    public String register(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult){
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration_form";
        }
        userDto = userAuthenticationService.addUser(userDto);
        return "redirect:/login";
    }

    @GetMapping("/courseEnrollment/{courseName}")
    public String getCourseDetails(@PathVariable("courseName") String courseName, @NotNull Model model,
                                   CourseDto courseDto, @NotNull Principal principal){
        courseDto = courseServiceImp.findCourse(courseName);
        try {
            String name = principal.getName();
            UserDto userDto = userAuthenticationService.getUser(name);
            model.addAttribute("user", userDto.getId());
            model.addAttribute("courseName", courseDto.getCourseName());
            model.addAttribute("courseContent", courseDto.getCourseContent());
            model.addAttribute("courseInstructor", courseDto.getInstructorName());
            return "course_a_details";
        }catch(NullPointerException e){
            model.addAttribute("courseName", courseDto.getCourseName());
            model.addAttribute("courseContent", courseDto.getCourseContent());
            model.addAttribute("courseInstructor", courseDto.getInstructorName());
            return "course_p_details";
        }
    }

    @Loggable1
    @GetMapping("/course-enrollment-message/{courseName}")
    public String doEnrollment(@PathVariable("courseName") String courseName,
                                @NotNull Principal principal, Model model){
        UserDto userDto = userAuthenticationService.getUser(principal.getName());
        CourseDto courseDto = courseServiceImp.findCourse(courseName);
        CourseEnrollmentDto courseEnrollmentDto = courseEnrollmentServiceImp.
                                                    addEnrollment(userDto, courseDto);
        model.addAttribute("user",userDto.getId());
        return "enrollment_message";
    }
    @GetMapping("/profile/{user-name}")
    public String getProfile(@NotNull Principal principal, @NotNull Model model){
        UserDto userDto = userAuthenticationService.getUser(principal.getName());
        List<CourseEnrollmentDto> courseEnrollmentDtoList = courseEnrollmentServiceImp.
                getEnrollment(userDto.getId());
        List<CourseDto> courseDtoList = new ArrayList<>();
        for(CourseEnrollmentDto elem: courseEnrollmentDtoList){
            CourseDto courseDto = courseServiceImp.getCourse(elem.getCourseId());
            courseDtoList.add(courseDto);
        }
        model.addAttribute("user", userDto);
        model.addAttribute("course", courseDtoList);
        return "user_profile";
    }


}
