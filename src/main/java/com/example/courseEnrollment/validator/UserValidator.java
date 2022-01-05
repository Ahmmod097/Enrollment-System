package com.example.courseEnrollment.validator;

import com.example.courseEnrollment.dto.UserDto;
import com.example.courseEnrollment.model.User;
import com.example.courseEnrollment.repository.UserRepository;
import com.example.courseEnrollment.service.UserAuthenticationService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    public UserValidator( UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto)target;
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userDto.email");
        }
    }
}
