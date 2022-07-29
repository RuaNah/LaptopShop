package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.laptop.R;
import com.example.laptop.adapter.AcerAdapter;
import com.example.laptop.adapter.AsusAdapter;
import com.example.laptop.model.Product;
import com.example.laptop.retrofit.Api;
import com.example.laptop.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcerActivity extends AppCompatActivity {
    Toolbar toolbarAcer;
    RecyclerView rcvAcer;
    Api api;
    AcerAdapter acerAdapter;
    List<Product> listProduct,listAcer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acer);
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
                    if (p.getProducttype().equals("Acer")){
                        listAcer.add(p);
                    }
                }
                acerAdapter = new AcerAdapter(getApplicationContext(),listAcer);
                rcvAcer.setAdapter(acerAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        toolbarAcer = findViewById(R.id.toolbar_Acer);
        rcvAcer = findViewById(R.id.rcvAcer);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvAcer.setLayoutManager(layoutManager);
        rcvAcer.setHasFixedSize(true);
        listAcer =  new ArrayList<>();
    }
}