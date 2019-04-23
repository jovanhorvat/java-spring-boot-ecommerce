package com.example.shop.service.impl;

import com.example.shop.domain.Product;
import com.example.shop.domain.ProductCategory;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class CategoryServiceImpl {

    @Autowired
    CategoryRepository repo;

    public List<ProductCategory> getAllCategories() {
        return repo.findAll();
    }

    public ProductCategory getCategoryById(long id) {
        Optional<ProductCategory> category = repo.findById(id);

        if (category.isPresent())
            return category.get();
        else throw new NoSuchElementException("Category doesn't exist in DB");
    }

}
