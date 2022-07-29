package com.example.laptop.model;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import retrofit2.http.Field;

public class Order {
    @SerializedName("address")
    String address;
    @SerializedName("phonenumber")
    String phonenumber;
    @SerializedName("dateorder")
    String dateorder;
    @SerializedName("no")
    String no;
    @SerializedName("totalprice")
    String totalprice;
    @SerializedName("customername")
    String customnername;
    @SerializedName("listorder")
    List<Cart> listorder;
    @SerializedName("email")
    String email;
    @SerializedName("username")
    String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Order(String address, String phonenumber, String dateorder,
                 String no, String customnername, String totalprice, List<Cart> listCart, String email, String username) {
        this.address = address;
        this.phonenumber = phonenumber;
        this.dateorder = dateorder;
        this.customnername = customnername;
        this.no = no;
        this.totalprice = totalprice;
        this.listorder = listCart;
        this.email = email;
        this.username = username;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDateorder() {
        return dateorder;
    }

    public void setDateorder(String dateorder) {
        this.dateorder = dateorder;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getCustomnername() {
        return customnername;
    }

    public void setCustomnername(String customnername) {
        this.customnername = customnername;
    }

    public List<Cart> getList() {
        return listorder;
    }

    public void setList(List<Cart> list) {
        this.listorder = list;
    }

}
