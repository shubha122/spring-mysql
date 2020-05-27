package com.example.springmysql.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class CreateUserRequest {

    @Length(min=2,max=10,message="First name must not be less than 2 characters")
    @NotBlank(message="first name can not be blank")
    String fname ;
    @Length(min=2,max=10,message="Last name must not be less than 2 characters")
    String lname;
    @NotBlank(message = "Email cannot be missing or empty")
    @Email
    String email;
    String gender;
    @NotBlank(message = "Password cannot be missing or empty")
    String password;
    String country;
    String city;
}
