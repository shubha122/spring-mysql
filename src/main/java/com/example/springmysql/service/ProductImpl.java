package com.example.springmysql.service;

import com.example.springmysql.dao.ProductDao;
import com.example.springmysql.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findBySubCategoryIdAndBrand(int subcategory_id, String brand) {
        return productDao.findBySubCategoryIdAndBrand(subcategory_id, brand);
    }
}
