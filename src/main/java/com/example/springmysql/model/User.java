package com.example.springmysql.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank(message = "fname is mandatory")
    @Length(max = 100)
    private String fname ;
    @Length(max = 100)
    private String lname;

    @NotBlank(message = "email is mandatory")
    private String email;
    private String gender;
    @NotBlank(message = "password is mandatory")
    private String password;
    private String country;
    private String city;



}

