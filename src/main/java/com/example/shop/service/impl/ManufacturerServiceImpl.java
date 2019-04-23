package com.example.shop.service.impl;

import com.example.shop.domain.ProductManufacturer;
import com.example.shop.repository.ManufacturerRepository;
import com.example.shop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl {

    @Autowired
    ManufacturerRepository repo;

    public List<ProductManufacturer> getAllManufacturers() {
        return repo.findAll();
    }

    public ProductManufacturer getManufacturerById(long id) {
        Optional<ProductManufacturer> manufacturer = repo.findById(id);

        if (manufacturer.isPresent())
            return manufacturer.get();
        else throw new NoSuchElementException("Manufacturer doesn't exist in DB");
    }
}
