package com.example.springmysql.dao;

import com.example.springmysql.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category,Integer> {


}
