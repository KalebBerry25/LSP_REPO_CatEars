package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

public class Product {
    private final int productId;
    private final String originalCategory;

    private final String name;
    private final BigDecimal price;
    private final String category;

    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.originalCategory = category; 
    }

    private Product(int productId, String name, BigDecimal price, String category, String originalCategory) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.originalCategory = originalCategory;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getCategory() { return category; }
    public String getOriginalCategory() { return originalCategory; }

    public Product withName(String newName) {
        return new Product(productId, newName, price, category, originalCategory);
    }

    public Product withPrice(BigDecimal newPrice) {
        return new Product(productId, name, newPrice, category, originalCategory);
    }

    public Product withCategory(String newCategory) {
        return new Product(productId, name, price, newCategory, originalCategory);
    }
}