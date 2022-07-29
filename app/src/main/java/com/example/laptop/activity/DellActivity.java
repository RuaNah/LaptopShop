package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.laptop.R;
import com.example.laptop.adapter.AsusAdapter;
import com.example.laptop.adapter.DellAdapter;
import com.example.laptop.model.Product;
import com.example.laptop.retrofit.Api;
import com.example.laptop.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DellActivity extends AppCompatActivity {
    Toolbar toolbarDell;
    RecyclerView rcvDell;
    Api api;
    DellAdapter dellAdapter;
    List<Product> listProduct,listDell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dell);
        api = RetrofitClient.getClient().create(Api.class);
        AnhXa();
        getData();
    }
    private void getData() {
        Call<List<Product>> createCall = api.allProduct();
        createCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                listProduct = response.body();
                for(Product p : listProduct)
                {
                    if (p.getProducttype().equals("Dell")){
                        listDell.add(p);
                    }
                }
                dellAdapter = new DellAdapter(getApplicationContext(),listDell);
                rcvDell.setAdapter(dellAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        toolbarDell = findViewById(R.id.toolbar_Dell);
        rcvDell = findViewById(R.id.rcvDell);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvDell.setLayoutManager(layoutManager);
        rcvDell.setHasFixedSize(true);
        listDell =  new ArrayList<>();
    }
}