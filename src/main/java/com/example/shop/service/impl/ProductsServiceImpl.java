package com.example.shop.service.impl;

import com.example.shop.domain.Product;
import com.example.shop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductsServiceImpl {

    @Autowired
    ProductsRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(long id) {
        Optional<Product> product = repo.findById(id);

        if (product.isPresent())
            return product.get();
        else throw new NoSuchElementException("Product doesn't exist in DB");
    }

    public void save(Product p) {
        repo.save(p);
    }

    public void delete(Product p) {
       repo.deleteById(p.id);
    }

    public List<Product> getProductsByCatId(long categoryId) {
        Optional<List<Product>> products = repo.getProductsByCatId(categoryId);

        if (products.isPresent())
            return products.get();
        else throw new NoSuchElementException("No products matched the query");

    }

    public List<Product> getProductsByCatIdAndManufacturerId(long categoryId, long manufacturerId) {
        Optional<List<Product>> products = repo.getProductsByCatId(categoryId, manufacturerId);

        if (products.isPresent())
            return products.get();
        else throw new NoSuchElementException("No products matched the query");
    }

    public int getSumByCatId(long catId) {
        return repo.getSumByCatId(catId);
    }
}
