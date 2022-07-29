package com.example.laptop.retrofit;

import com.example.laptop.model.Cart;
import com.example.laptop.model.Category;
import com.example.laptop.model.Order;
import com.example.laptop.model.Product;
import com.example.laptop.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    //User
    @GET("api/users")
    Call<List<User>> allUser();

    @POST("api/users")
    Call<User> createUser(@Body User user);
    //Product
    @GET("api/products")
    Call<List<Product>> allProduct();

    //Category
    @GET("api/categories")
    Call<List<Category>> allCategory();

    //Order
    @GET("api/carts")
    Call<List<Order>> allOrder();

    @POST("api/carts")
    Call<Order> createOrder(@Body Order order);

}
