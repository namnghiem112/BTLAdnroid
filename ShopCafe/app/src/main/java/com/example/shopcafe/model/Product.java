package com.example.shopcafe.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String urlImg;
    private String productName;
    private String description;
    private String brand;
    private double productPrice;

    private int numProduct;

    public Product() {
    }

    public Product(int id, String urlImg, String productName, String description, String brand, double productPrice, int numProduct) {
        this.id = id;
        this.urlImg = urlImg;
        this.productName = productName;
        this.description = description;
        this.brand = brand;
        this.productPrice = productPrice;
        this.numProduct = numProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", urlImg='" + urlImg + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", productPrice=" + productPrice +
                ", numProduct=" + numProduct +
                '}';
    }
}
