package com.coursework.langschoolweb.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;

}
