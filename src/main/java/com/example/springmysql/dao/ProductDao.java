package com.example.springmysql.dao;

import com.example.springmysql.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDao extends CrudRepository<Product,Integer> {


    @Query(value="select * from product p where (p.subcategory_id =?1 and p.brand = ?2)",nativeQuery=true)
    List<Product> findBySubCategoryIdAndBrand(int subcategory_id, String brand);


}
