package com.example.laptop.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("name")
    String name;
    //List<Product> arrayList;
    @SerializedName("image")
    String image;

    public Category(String name, String image) {
        this.name = name;
        //this.arrayList = arrayList;
        this.image = image;
    }

    public Category(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<Product> getArrayList() {
        return arrayList;
    }*/

    /*public void setArrayList(List<Product> arrayList) {
        this.arrayList = arrayList;
    }*/

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
