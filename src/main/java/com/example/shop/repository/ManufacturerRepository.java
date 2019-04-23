package com.example.shop.repository;

import com.example.shop.domain.ProductManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<ProductManufacturer, Long> {
}
