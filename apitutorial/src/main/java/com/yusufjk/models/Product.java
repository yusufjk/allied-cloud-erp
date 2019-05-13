package com.yusufjk.models;

public class Product {
    private String productName;
    private int size;
    private int qty;
    private int stock;

    public Product() {
    }

    public Product(String productName, int qty, int size, int stock) {
        this.setProductName(productName);
        this.setQty(qty);
        this.setSize(size);
        this.setStock(stock);
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
