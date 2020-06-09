package com.example.springmysql.service;

import com.example.springmysql.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findBySubCategoryIdAndBrand(int subcategory_id, String brand);
}
