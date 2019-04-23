package com.example.shop.service;

import com.example.shop.domain.Product;
import com.example.shop.domain.ProductCategory;
import com.example.shop.domain.ProductManufacturer;

import java.util.List;

public interface CategoryService {
    public List<ProductCategory> getAllCategories();
    public ProductCategory getCategoryById(long id);
}
