package com.example.laptop.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    String email;
    @SerializedName("username")
    String username;
    @SerializedName("password")
    String password;
    @SerializedName("birthday")
    String bitrhday;

    public String getBitrhday() {
        return bitrhday;
    }

    public void setBitrhday(String bitrhday) {
        this.bitrhday = bitrhday;
    }

    public User(String email, String username, String password,String birthday) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.bitrhday = birthday;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
    }
}
