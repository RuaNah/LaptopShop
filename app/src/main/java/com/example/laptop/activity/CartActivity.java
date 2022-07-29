package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.laptop.R;
import com.example.laptop.adapter.CartAdapter;
import com.example.laptop.model.event.EventSumTotal;
import com.example.laptop.ultils.Ultils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    TextView rong,tongtien;
    Toolbar toolbarCart;
    RecyclerView rcvCart;
    Button btnBuy;
    CartAdapter cartAdapter;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        initControl();
        sumTotal();
    }
    //tinh tong tien
    private void sumTotal() {
        total = 0;
        for (int i = 0; i < Ultils.listCart.size(); i++) {
            total += (Ultils.listCart.get(i).getPrice()*Ultils.listCart.get(i).getQuantity());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien.setText(decimalFormat.format(total));
    }

    private void initControl() {
        if(Ultils.listCart.size() == 0){
            rong.setVisibility(View.VISIBLE);
        }
        else{
            cartAdapter = new CartAdapter(getApplicationContext(),Ultils.listCart);
            rcvCart.setAdapter(cartAdapter);
        }
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PayActivity.class);
                intent.putExtra("total",total);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        rong = findViewById(R.id.tv_cart);
        toolbarCart = findViewById(R.id.toolbar_cart);
        rcvCart = findViewById(R.id.rcv_cart);
        tongtien = findViewById(R.id.tv_cart_total);
        btnBuy = findViewById(R.id.btnBuy);
        rcvCart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvCart.setLayoutManager(layoutManager);
    }
    //Dang ky + huy dang ky event clcik tren nut them
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void eventSumTotal(EventSumTotal event)
    {
        if (event != null){
            sumTotal();
        }
    }
}