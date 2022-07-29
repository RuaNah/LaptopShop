package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.laptop.R;
import com.example.laptop.adapter.DellAdapter;
import com.example.laptop.adapter.LenovoAdapter;
import com.example.laptop.adapter.MacbookAdapter;
import com.example.laptop.model.Product;
import com.example.laptop.retrofit.Api;
import com.example.laptop.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LenovoActivity extends AppCompatActivity {
    Toolbar toolbarLenovo;
    RecyclerView rcvLenovo;
    Api api;
    LenovoAdapter LenovoAdapter;
    List<Product> listProduct,listLenovo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenovo);
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
                    if (p.getProducttype().equals("Lenovo")){
                        listLenovo.add(p);
                    }
                }
                LenovoAdapter = new LenovoAdapter(getApplicationContext(),listLenovo);
                rcvLenovo.setAdapter(LenovoAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        toolbarLenovo = findViewById(R.id.toolbar_Lenovo);
        rcvLenovo = findViewById(R.id.rcvLenovo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvLenovo.setLayoutManager(layoutManager);
        rcvLenovo.setHasFixedSize(true);
        listLenovo =  new ArrayList<>();
    }
}