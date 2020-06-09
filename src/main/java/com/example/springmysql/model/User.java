package com.example.springmysql.model;

import lombok.*;
import org.hibernate.validator.constraints.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="users")
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
    @Length(max = 50)
    @Column(unique = true)
    private String email;

    private String gender;

    @NotBlank(message = "password is mandatory")
    private String password;

    private String country;

    private String city;
}

