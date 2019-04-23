package com.example.shop.service;

import com.example.shop.domain.Product;

import java.util.List;

public interface ProductsService {
    public List<Product> getAllProducts();
    public Product getProductById(long id);
    public void save(Product p);
    public void delete(Product p);
    public List<Product> getProductsByCatId(long categoryId);
    public List<Product> getProductsByCatIdAndManufacturerId(long categoryId, long manufacturerId);
    public int getSumByCatId(long catId);
}
