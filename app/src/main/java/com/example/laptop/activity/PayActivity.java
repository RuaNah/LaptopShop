package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.laptop.R;
import com.example.laptop.model.Order;
import com.example.laptop.retrofit.Api;
import com.example.laptop.retrofit.RetrofitClient;
import com.example.laptop.ultils.Ultils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;

public class PayActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView total;
    EditText name,phone,email,location;
    Button btnDatHang;
    Api api;
    List<Order> lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        initControl();
    }

    private void initControl() {

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString().trim();
                String phone1 = phone.getText().toString().trim();
                String email1 = email.getText().toString().trim();
                String location1 = location.getText().toString().trim();
                String total1 = total.getText().toString().trim();
                if (TextUtils.isEmpty(name1) || TextUtils.isEmpty(phone1) || TextUtils.isEmpty(email1) || TextUtils.isEmpty(location1)){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    Date date = Calendar.getInstance().getTime();
                    Random rd=new Random();
                    String no = "A"+rd.nextInt(1000);
                    Order order = new Order(location1,phone1,
                            date.toString(),no,name1,total1,Ultils.listCart,email1,Ultils.current_user);

                    Call<Order> orderCall = api.createOrder(order);
                    orderCall.enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {
                            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            Ultils.listCart.clear();
                            startActivity(intent);
                            finish();
                        }
                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            Ultils.listCart.clear();
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int total1 = getIntent().getIntExtra("total",0);
        total.setText(decimalFormat.format(total1));
    }

    private void initView() {
        api = RetrofitClient.getClient().create(Api.class);
        toolbar = findViewById(R.id.toolbar_pay);
        total = findViewById(R.id.total_pay);
        name = findViewById(R.id.name_pay);
        phone = findViewById(R.id.phone_pay);
        email = findViewById(R.id.email_pay);
        location = findViewById(R.id.location_pay);
        btnDatHang = findViewById(R.id.btnPay);
        lst = new ArrayList<>();
    }
}