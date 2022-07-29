package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.laptop.R;
import com.example.laptop.adapter.ProductAdapter;
import com.example.laptop.model.Product;
import com.example.laptop.retrofit.Api;
import com.example.laptop.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbarSearch;
    RecyclerView rcvSearch;
    EditText edtSearch;
    Api api;
    ProductAdapter productAdapter;
    List<Product> lstProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        api = RetrofitClient.getClient().create(Api.class);
        toolbarSearch = findViewById(R.id.toolbar_Search);
        rcvSearch = findViewById(R.id.rcvSearch);
        edtSearch = findViewById(R.id.edt_search);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rcvSearch.setLayoutManager(layoutManager);
        rcvSearch.setHasFixedSize(true);
        lstProduct = new ArrayList<>();
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                lstProduct = new ArrayList<>();
                getAllProduct();
            }
        });
    }
    private void getAllProduct() {
        String search = edtSearch.getText().toString().trim();
        Call<List<Product>> createCall = api.allProduct();
        createCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                for (Product p: response.body()) {
                    if (p.getName().toLowerCase().contains(search.toLowerCase())){
                        lstProduct.add(p);
                    }
                    productAdapter = new ProductAdapter(getApplicationContext(),lstProduct);
                    rcvSearch.setAdapter(productAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}