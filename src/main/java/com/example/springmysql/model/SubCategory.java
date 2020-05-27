package com.example.springmysql.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "subcategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",nullable = false)
    private int id;
    @Column(name="NAME",nullable = false)
    private String name;
    @OneToOne
    @JoinColumn(name="category_id",referencedColumnName = "ID")
    private Category category;
}
