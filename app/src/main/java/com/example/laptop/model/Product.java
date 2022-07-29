package com.example.laptop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product  implements Serializable {

    @SerializedName("image")
    String image;

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    @SerializedName("price")
    int price;

    @SerializedName("producttype")
    String producttype;


    @SerializedName("quantity")
    int quantity;

    public Product() {
    }

    public Product(String image, String name, String description,String producttype, int price, int quantity) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.producttype = producttype;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
