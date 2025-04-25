package com.supplychain.models;

public class Product {
    private  String code;
    private  String name;
    private  String category="";

    public Product(String code, String name, String category) {
        this.code = code;
        this.name = name;
        this.category = category;
    }

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
