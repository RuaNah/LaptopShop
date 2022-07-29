package com.example.laptop.activity;

import static com.example.laptop.retrofit.RetrofitClient.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laptop.R;
import com.example.laptop.model.User;
import com.example.laptop.retrofit.Api;
import com.example.laptop.retrofit.RetrofitClient;
import com.example.laptop.ultils.Ultils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Button btn_login_finger;
    Button btnCancel,btnOK;
    Api api;
    List<User> listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        api = RetrofitClient.getClient().create(Api.class);
        btn_login_finger = findViewById(R.id.btn_finger);
        btn_login_finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LoginFingerActivity.class);
                startActivity(intent);
            }
        });

        final EditText edtUsername = findViewById(R.id.edt_username);
        final EditText edtPassword = findViewById(R.id.edt_password);
        final Button btn_Signin = findViewById(R.id.btnSignin);
        final Button btnRegisters = findViewById(R.id.btnRegister);
        listUser = new ArrayList<>();
        getListUser();
        btn_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtUsername.getText().toString().isEmpty() ||
                        edtPassword.getText().toString().isEmpty()){
                    final Dialog dialog = new Dialog(LoginActivity.this);
                    dialog.setContentView(R.layout.dialog_custom);
                    btnOK = dialog.findViewById(R.id.btnOK);
                    btnCancel = dialog.findViewById(R.id.btnCancel);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                            startActivityForResult(intent, 100);
                            dialog.dismiss();
                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                }else if(edtPassword.getText().toString().length() < 6){
                    edtPassword.setError("Minimum 6 number");
                }
                else
                {
                    if (listUser == null || listUser.isEmpty())
                    {
                        return;
                    }
                    boolean notNull = false;

                    for (User user : listUser)
                    {
                        if (edtUsername.getText().toString().equals(user.getUsername())
                                &&edtPassword.getText().toString().equals(user.getPassword()))
                        {
                            notNull = true;

                            break;
                        }

                    }
                    if (notNull == true)
                    {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("username",edtUsername.getText().toString());
                        intent.putExtra("password",edtPassword.getText().toString());
                        Log.e("user",edtUsername.getText().toString());
                        Log.e("pass",edtPassword.getText().toString());
                        Ultils.current_user = edtUsername.getText().toString();
                        startActivity(intent);
                        //Toast.makeText(LoginActivity.this,"Thanh cong",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnRegisters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,100);
            }
        });
    }

    private void getListUser() {
        Call<List<User>> listCall = api.allUser();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                listUser = response.body();
                Log.e("List User",listUser.size()+ "");
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Call api fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}