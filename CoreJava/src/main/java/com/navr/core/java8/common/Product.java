package com.navr.core.java8.common;

public class Product {
    private long productId;
    private String brandName;
    private String productName;
    private double price;

    public Product() {

    }

    public Product(long productId, String brandName, String productName, double price) {
        this.productId = productId;
        this.brandName = brandName;
        this.productName = productName;
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", brandName='" + brandName + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}

