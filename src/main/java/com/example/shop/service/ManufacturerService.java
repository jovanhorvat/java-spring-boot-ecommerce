package com.example.shop.service;

import com.example.shop.domain.ProductManufacturer;

import java.util.List;

public interface ManufacturerService {
    public List<ProductManufacturer> getAllManufactureres();
    public ProductManufacturer getManufactererById(long id);
}

