package com.example.courseEnrollment.service;

import com.example.courseEnrollment.dto.UserDto;
import com.example.courseEnrollment.model.User;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto getUser(String email);
}
