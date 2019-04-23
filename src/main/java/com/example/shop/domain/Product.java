package com.example.shop.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String productName;
    public String productDescription;

    @ManyToOne
    @JoinColumn(name = "man_id")
    public ProductManufacturer productManufacturer;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    public ProductCategory productCategory;

    @ManyToMany
    @JoinTable(name = "product_accessory",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name = "accessory"))
    private List<Accessory> accesories;

    @Lob
    public byte[] byteImage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public ProductManufacturer getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(ProductManufacturer productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<Accessory> getAccesories() {
        return accesories;
    }

    public void setAccesories(List<Accessory> accesories) {
        this.accesories = accesories;
    }

    public byte[] getByteImage() {
        return byteImage;
    }

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }
}
