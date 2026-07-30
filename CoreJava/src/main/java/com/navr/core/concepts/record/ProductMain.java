package com.navr.core.concepts.record;

import java.math.BigDecimal;

public class ProductMain {
    public static void main(String[] args) {
        Product product1 = createProduct1();
        Product product2 = createProduct2();
        compareProduct(product1, product2);
        Product p3 = createProductWithDefaultType();
        createInvalidProduct();
    }

    private static Product createProduct1() {
        Product p = new Product("Laptop", new BigDecimal("999.99"), "ELECTRONICS");
        System.out.printf("createProduct1 -> Product created successfully. %s%n", p);
        printProduct(p);
        return p;
    }

    private static void printProduct(Product p) {
        System.out.printf("printProduct -> Product Name: %s, Price: %s, Type: %s%n%n", p.name(), p.price(), p.type());
    }

    private static Product createProduct2() {
        Product p = new Product("Laptop", new BigDecimal("999.99"), "ELECTRONICS");
        System.out.printf("createProduct2 -> Product created successfully. %s%n", p);
        printProduct(p);
        return p;
    }

    private static Product createProductWithDefaultType() {
        Product p = new Product("Laptop", new BigDecimal("499.99"));
        System.out.printf("createProductWithDefaultType -> Product created successfully with default type: %s%n", p);
        printProduct(p);
        return p;
    }

    private static void createInvalidProduct() {
        try {
            Product p = new Product("Laptop", new BigDecimal("999.99"), "ELECTRONICS");
        } catch (IllegalArgumentException e) {
            System.out.println("createInvalidProduct -> Invalid product created: " + e.getMessage());
        }
    }

    private static void compareProduct(Product p1, Product p2) {
        boolean equals = p1.equals(p2);
        System.out.printf("compareProduct -> Product1 equals Product2: %b%n%n", equals);
    }
}
