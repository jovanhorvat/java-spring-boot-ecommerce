package com.example.shop.web;

import com.example.shop.domain.Product;
import com.example.shop.service.impl.CategoryServiceImpl;
import com.example.shop.service.impl.ProductsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/")
public class ProductsResources {

    private ProductsServiceImpl productsService;
    private CategoryServiceImpl categoryService;

    public ProductsResources(ProductsServiceImpl productsService) {
        this.productsService = productsService;
    }

    @GetMapping("products")
    public List<Product> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public Product getProductById(@PathVariable final long id) {
        return productsService.getProductById(id);
    }

    @GetMapping("products/category/{catId}")
    public List<Product> getProductsByCatId(@PathVariable final long catId) { return productsService.getProductsByCatId(catId); }

    @GetMapping("products/category/{catId}/manufacturer/{manId}")
    public List<Product> getProductsByCatIdAndManufacturerId(@PathVariable final long catId, @PathVariable final long manId) { return productsService.getProductsByCatIdAndManufacturerId(catId, manId); }

    @GetMapping("products/category/{catId}/price")
    public int sumByCatId(@PathVariable final long catId) { return productsService.getSumByCatId(catId); };

    /*
    @GetMapping("products/category/{categoryId}")
    public List<Product> getProductsByCatId(@PathVariable final long categoryId) {
        return productsService.getProductsByCatId(categoryId);
   }
   */
}
