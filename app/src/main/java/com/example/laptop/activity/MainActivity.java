package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.laptop.R;
import com.example.laptop.adapter.CategoriesAdapter;
import com.example.laptop.adapter.ProductAdapter;
import com.example.laptop.model.Category;
import com.example.laptop.model.Product;
import com.example.laptop.retrofit.Api;
import com.example.laptop.retrofit.RetrofitClient;
import com.example.laptop.ultils.Ultils;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView rcvMain;
    NavigationView navbarMain;
    ListView listNavMain;
    CategoriesAdapter categoriesAdapter;
    List<Category> listCate;
    Api api;
    ProductAdapter productAdapter;
    List<Product> listProduct;
    NotificationBadge badge;
    FrameLayout frameLayout;
    ImageView imgSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = RetrofitClient.getClient().create(Api.class);
        AnhXa();
        ActionViewFlipper();
        getCategory();
        getAllProduct();
        getEventClickCategory();
    }

    private void getEventClickCategory() {
        listNavMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    //0 asus,1 hp, 2 mac, 3 lenovo, 4 acer, 5 dell, 6 thống kê
                    case 0:
                        Intent asus = new Intent(getApplicationContext(),AsusActivity.class);
                        startActivity(asus);
                        break;
                    case 1:
                        Intent hp = new Intent(getApplicationContext(),HpActivity.class);
                        startActivity(hp);
                        break;
                    case 2:
                        Intent mac = new Intent(getApplicationContext(),MacbookActivity.class);
                        startActivity(mac);
                        break;
                    case 3:
                        Intent lenovo = new Intent(getApplicationContext(),LenovoActivity.class);
                        startActivity(lenovo);
                        break;
                    case 4:
                        Intent acer = new Intent(getApplicationContext(),AcerActivity.class);
                        startActivity(acer);
                        break;
                    case 5:
                        Intent dell = new Intent(getApplicationContext(),DellActivity.class);
                        startActivity(dell);
                        break;
                    case 6:
                        Intent statistical = new Intent(getApplicationContext(),StatisticalActivity.class);
                        startActivity(statistical);
                        break;
                    case 7:
                        Intent promotion = new Intent(getApplicationContext(),BarcodeScannerActivity.class);
                        startActivity(promotion);
                        break;
                }
            }
        });
    }

    private void getAllProduct() {
        Call<List<Product>> createCall = api.allProduct();
        createCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                listProduct =response.body();
                productAdapter = new ProductAdapter(getApplicationContext(),listProduct);
                rcvMain.setAdapter(productAdapter);
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void getCategory() {
        Call<List<Category>> createCall = api.allCategory();
        createCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                listCate = response.body();
                //gán adaper
                categoriesAdapter = new CategoriesAdapter(getApplicationContext(),listCate);
                listNavMain.setAdapter(categoriesAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    private void ActionViewFlipper() {
        List<String> flip = new ArrayList<>();
        flip.add("https://firebasestorage.googleapis.com/v0/b/database-6ad8f.appspot.com/o/slide_photo.png?alt=media&token=e102dbc9-f7fe-437c-9cce-75df6373768b");
        flip.add("https://firebasestorage.googleapis.com/v0/b/database-6ad8f.appspot.com/o/slide_photo2.png?alt=media&token=8ae464fb-a896-4f0a-b106-590fefc3fc94");
        flip.add("https://firebasestorage.googleapis.com/v0/b/database-6ad8f.appspot.com/o/slide_photo3.png?alt=media&token=d19f493e-be71-4290-97ed-e6f296ec6181");
        flip.add("https://firebasestorage.googleapis.com/v0/b/database-6ad8f.appspot.com/o/slide_photo4.png?alt=media&token=e9a3d780-6be1-4fb5-aa97-ea5a48361596");
        flip.add("https://firebasestorage.googleapis.com/v0/b/database-6ad8f.appspot.com/o/slide_photo5.png?alt=media&token=ab8bd7bc-56cf-4b74-92b6-81dc4685f92e");
        for (int i = 0; i < flip.size(); i++) {
            ImageView img= new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(flip.get(i)).into(img);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(img);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
    }


    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarMain);
        viewFlipper = findViewById(R.id.viewFlipMain);
        rcvMain = findViewById(R.id.rcvMain);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rcvMain.setLayoutManager(layoutManager);
        rcvMain.setHasFixedSize(true);
        navbarMain = findViewById(R.id.navMain);
        listNavMain = findViewById(R.id.listNavMain);
        badge = findViewById(R.id.badge_sl);
        frameLayout = findViewById(R.id.frame_cart);
        imgSearch = findViewById(R.id.search);
        listCate = new ArrayList<>();
        listProduct = new ArrayList<>();
        if(Ultils.listCart == null)
        {
            Ultils.listCart = new ArrayList<>();
        }else {
            int totalItem = 0;
            for (int i = 0; i < Ultils.listCart.size(); i++) {
                totalItem += Ultils.listCart.get(i).getQuantity();

            }
            badge.setText(String.valueOf(totalItem));
        }

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(cart);
            }
        });


        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem = 0;
        for (int i = 0; i < Ultils.listCart.size(); i++) {
            totalItem += Ultils.listCart.get(i).getQuantity();

        }
        badge.setText(String.valueOf(totalItem));
    }
}