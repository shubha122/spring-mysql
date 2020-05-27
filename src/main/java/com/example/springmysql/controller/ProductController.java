package com.example.springmysql.controller;

import com.example.springmysql.dao.ProductDao;
import com.example.springmysql.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
@Autowired
    private ProductDao productDao ;

//select * from products where subcategory_id = 123 and brand = "apple";

@GetMapping("/getProducts")
    public List<Product> getProduct(@RequestParam(required = false) int subcategory_id,@RequestParam (required = false) String brand){
    return productDao.findBySubCategoryIdAndBrand(subcategory_id,brand);
    }
}
