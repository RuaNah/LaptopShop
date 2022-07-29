package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.laptop.R;
import com.example.laptop.adapter.DellAdapter;
import com.example.laptop.adapter.MacbookAdapter;
import com.example.laptop.model.Product;
import com.example.laptop.retrofit.Api;
import com.example.laptop.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MacbookActivity extends AppCompatActivity {
    Toolbar toolbarMac;
    RecyclerView rcvMac;
    Api api;
    MacbookAdapter MacAdapter;
    List<Product> listProduct,listMac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macbook);
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
                    if (p.getProducttype().equals("Macbook")){
                        listMac.add(p);
                    }
                }
                MacAdapter = new MacbookAdapter(getApplicationContext(),listMac);
                rcvMac.setAdapter(MacAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        toolbarMac= findViewById(R.id.toolbar_Macbook);
        rcvMac = findViewById(R.id.rcvMacbook);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvMac.setLayoutManager(layoutManager);
        rcvMac.setHasFixedSize(true);
        listMac =  new ArrayList<>();
    }
}