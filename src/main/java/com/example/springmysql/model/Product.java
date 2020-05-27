package com.example.springmysql.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID",nullable = false)
    private int id;
    @Column(name="NAME",nullable = false)
    private String name;
    @OneToOne
    @JoinColumn(name="subcategory_id",referencedColumnName = "ID")
    private SubCategory subCategory;
    @Column(name="BRAND",nullable = false)
    private String brand;
    @Column(name="PRICE",nullable = false)
    private float price;
    @Column(name="PRODUCT_CODE",nullable = false,unique = true)
    private long product_code;

}
