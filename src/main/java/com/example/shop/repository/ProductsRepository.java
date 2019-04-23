package com.example.shop.repository;

import com.example.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM Product p WHERE p.cat_id = :catId", nativeQuery = true)
    public Optional<List<Product>> getProductsByCatId(@Param("catId") long catId);

    @Query(value = "SELECT * FROM Product p WHERE p.cat_id = :catId AND p.man_id = :manId", nativeQuery = true)
    public Optional<List<Product>> getProductsByCatId(@Param("catId") long catId, @Param("manId") long manId);

    @Query(value = "SELECT SUM(cat_id) FROM Product p WHERE p.cat_id = :catId", nativeQuery = true)
    public int getSumByCatId(@Param("catId") long catId);
}
