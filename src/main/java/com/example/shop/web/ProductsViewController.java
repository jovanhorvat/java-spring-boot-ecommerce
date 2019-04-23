package com.example.shop.web;

import com.example.shop.domain.Product;
import com.example.shop.exceptions.ProductNotFoundException;
import com.example.shop.service.impl.CategoryServiceImpl;
import com.example.shop.service.impl.ManufacturerServiceImpl;
import com.example.shop.service.impl.ProductsServiceImpl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class ProductsViewController {

    private ProductsServiceImpl productsService;
    private ManufacturerServiceImpl manufacturerService;
    private CategoryServiceImpl categoryService;

    public ProductsViewController(ProductsServiceImpl productsService, ManufacturerServiceImpl manufacturerService, CategoryServiceImpl categoryService) {
        this.productsService = productsService;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    @GetMapping("products")
    public String ProductsView(Model model) {
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("message", "продукти");
        return "ProductsView";
    }

    @GetMapping("/products/{id}")
    public String productDetailsView(@PathVariable("id") String id,
                                     Model model) throws ProductNotFoundException {
        model.addAttribute("product", productsService.getProductById(Long.parseLong(id)));
        return "productDetailsView";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("productCategories", categoryService.getAllCategories());
        model.addAttribute("productManufacturers", manufacturerService.getAllManufacturers());
        return "AddProductView";
    }

    @PostMapping("/products")
    public String addProductPost(HttpServletRequest req, Model model,
                                 @RequestParam("productImage") MultipartFile file) throws IOException {
        String productName = req.getParameter("name");
        String productDescription = req.getParameter("desc");
        String productCategory = req.getParameter("category");
        String productManufacturer = req.getParameter("manufacturer");

        Product newProduct = new Product();
        newProduct.setProductName(productName);
        newProduct.setProductDescription(productDescription);
        newProduct.setProductCategory(categoryService.getCategoryById(Long.parseLong(productCategory)));
        newProduct.setProductManufacturer(manufacturerService.getManufacturerById(Long.parseLong(productManufacturer)));
        newProduct.setByteImage(file.getBytes());

        productsService.save(newProduct);
        /*
        switch (products.size()) {
            case 0:
                model.addAttribute("message", "Се' уште нема продукти");
                break;
            case 1:
                model.addAttribute("message", "Има 1 продукт во кошничката");
                break;
            default:
                model.addAttribute("message", "Има " + products.size() + " продукти во кошничката");
        }
        */
        model.addAttribute("products", productsService.getAllProducts());

        return "productsView";
    }

    @DeleteMapping("/products")
    public String deleteProduct(HttpServletRequest req, Model model) throws ProductNotFoundException {
        String productId = req.getParameter("productId");
        System.out.println(productId);
        productsService.delete(productsService.getProductById(Long.parseLong(productId)));

        model.addAttribute("products", productsService.getAllProducts());
        return "productsView";
    }

    @RequestMapping(value = "/image/{product_id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("product_id") Long productId) throws IOException, ProductNotFoundException {

        Product product = productsService.getProductById(productId);

        if (product == null)
            throw new ProductNotFoundException();


        byte[] imageContent = product.getByteImage();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.parse("attachment; filename="+ product.getProductName()));
        headers.setContentLength(imageContent.length);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imageContent.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(imageContent);
    }
}
