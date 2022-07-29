package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.laptop.R;
import com.example.laptop.adapter.CartAdapter;
import com.example.laptop.model.Cart;
import com.example.laptop.model.Product;
import com.example.laptop.ultils.Ultils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {
    TextView name,price,description;
    Button btnAddtoCart;
    ImageView img;
    Spinner spinner;
    Toolbar toolbarDetail;
    Product product;
    NotificationBadge badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initData();
        initControl();
    }

    private void initControl() {
        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
    }
    private void addToCart() {
        if (Ultils.listCart.size() > 0){// neu so luong trong gio hang > 0
            boolean flag = false;
            int sl = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0; i < Ultils.listCart.size(); i++) {
                if(Ultils.listCart.get(i).getName() == product.getName()){
                    Ultils.listCart.get(i).setQuantity(sl+Ultils.listCart.get(i).getQuantity());
                    Ultils.listCart.get(i).setPrice(product.getPrice());
                    flag = true;
                }
            }
            if (flag==false){//neu san pham khong trung trong gio hang tien hanh them item moi
                Cart cart = new Cart();
                cart.setName(product.getName());
                cart.setPrice(product.getPrice());
                cart.setImage(product.getImage());
                cart.setQuantity(sl);
                Ultils.listCart.add(cart);
            }
        }
        else {// = 0 tao moi mot item cart de add vao
            int sl = Integer.parseInt(spinner.getSelectedItem().toString());
            Cart cart = new Cart();
            cart.setName(product.getName());
            cart.setPrice(product.getPrice());
            cart.setImage(product.getImage());
            cart.setQuantity(sl);
            Ultils.listCart.add(cart);
        }

        int totalItem = 0;
        for (int i = 0; i < Ultils.listCart.size(); i++) {
            totalItem += Ultils.listCart.get(i).getQuantity();

        }
        badge.setText(String.valueOf(totalItem));
    }

    private void initData() {
        product = (Product)getIntent().getSerializableExtra("detail");
        name.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        price.setText(decimalFormat.format(product.getPrice()) + " VNĐ");
        description.setText(product.getDescription());
        Glide.with(getApplicationContext()).load(product.getImage()).into(img);
        Integer[] quantity  = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>
                (this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,quantity);
        spinner.setAdapter(arrayAdapter);
    }

    private void initView() {
        name = findViewById(R.id.name_detail);
        price = findViewById(R.id.price_detail);
        description = findViewById(R.id.description_detail);
        btnAddtoCart = findViewById(R.id.btnAddtoCart);
        img = findViewById(R.id.img_detail);
        spinner =  findViewById(R.id.spinner);
        toolbarDetail = findViewById(R.id.toolbar_detail);
        badge = findViewById(R.id.badge_sl);
        FrameLayout frameLayout = findViewById(R.id.frame_cart);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(cart);
            }
        });


        if (Ultils.listCart!=null){
            int totalItem = 0;
            for (int i = 0; i < Ultils.listCart.size(); i++) {
                totalItem += Ultils.listCart.get(i).getQuantity();

            }
            badge.setText(String.valueOf(totalItem));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Ultils.listCart!=null){
            int totalItem = 0;
            for (int i = 0; i < Ultils.listCart.size(); i++) {
                totalItem += Ultils.listCart.get(i).getQuantity();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }
}