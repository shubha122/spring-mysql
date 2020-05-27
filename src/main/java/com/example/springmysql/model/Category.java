package com.example.springmysql.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID",nullable = false)
    private int id;
    @Column(name ="NAME",nullable = false)
    private String name;

}
