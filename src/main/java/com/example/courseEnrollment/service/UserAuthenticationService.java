package com.example.courseEnrollment.service;

import com.example.courseEnrollment.dto.UserDto;
import com.example.courseEnrollment.model.User;
import com.example.courseEnrollment.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationService implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserAuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto addUser(@NotNull UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordHash(userDto.getPassword()));
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto getUser(String email) {
        User user = userRepository.findByEmail(email);
        UserDto userDto1 = new UserDto();
        userDto1.setId(user.getId());
        userDto1.setEmail(user.getEmail());
        userDto1.setFirstName(user.getFirstName());
        userDto1.setPassword(user.getPassword());
        userDto1.setLastName(user.getLastName());
        return userDto1;
    }

    private String passwordHash(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println(encodedPassword);
        return encodedPassword;
    }
}
