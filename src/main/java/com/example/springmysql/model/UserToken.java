package com.example.springmysql.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="user_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserToken {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "ID")
    private User user;

    @NotBlank(message = "token is mandatory")
    @Column(unique = true)
    @Length(max=150)
    private String token;

    @NotNull
    private Calendar created_date;
}
