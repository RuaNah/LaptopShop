package com.example.laptop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.laptop.R;
import com.example.laptop.model.User;
import com.example.laptop.retrofit.Api;
import com.example.laptop.retrofit.RetrofitClient;

import java.util.Calendar;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    Api api;
    private Button dateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        api = RetrofitClient.getClient().create(Api.class);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        final EditText edt_Email = findViewById(R.id.edtEmail);
        final EditText edt_User = findViewById(R.id.edtUsername);
        final EditText edt_Password = findViewById(R.id.edtPass);
        final EditText edt_Confirm = findViewById(R.id.edtCFPass);
        final Button btn_Create = findViewById(R.id.btnCreate);
        final Button  btn_Cancel = findViewById(R.id.btnCancelAC);
        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isValid(edt_Email.getText().toString())){
                    edt_Email.setError("Invalid Email Address");
                    return;
                }
                if(edt_User.getText().toString().isEmpty()){
                    edt_User.setError("Username cannot be null ");
                    return;
                }
                if(edt_Password.getText().toString().isEmpty()){
                    edt_Password.setError("Password required");
                    return;
                }
                if(edt_Confirm.getText().toString().isEmpty()){
                    edt_Confirm.setError("Password required");
                    return;
                }
                if(edt_Password.getText().toString().equals(edt_Confirm.getText().toString()))
                {
                    User user = new User(edt_Email.getText().toString(),edt_User.getText().toString(),edt_Password.getText().toString(),dateButton.getText().toString());
                    Call<User> createCall = api.createUser(user);
                    createCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                        }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            t.printStackTrace();

                        }
                    });
                    Intent intent = new Intent(RegisterActivity.this,
                            LoginActivity.class);
                    intent.putExtra("username",
                            edt_User.getText().toString());
                    intent.putExtra("password",
                            edt_Password.getText().toString());
                    setResult(101, intent);
                    finish();
                }else {
                    edt_Password.setError("Password and confirm password does not match");
                    edt_Confirm.setText("");
                    return;
                }
            }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }
    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

}