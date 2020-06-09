package com.example.springmysql.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Email cannot be missing or empty")
    @Email
    @Length(max = 50)
    @Column(unique = true)
    String email;

    @NotBlank(message = "Password cannot be missing or empty")
    String password;
}
