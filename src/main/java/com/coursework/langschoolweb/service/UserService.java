package com.coursework.langschoolweb.service;

import com.coursework.langschoolweb.dto.RegistrationDto;
import com.coursework.langschoolweb.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
