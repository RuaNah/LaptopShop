package com.example.laptop.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.laptop.ultils.Capture;
import com.example.laptop.R;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;


public class BarcodeScannerActivity extends AppCompatActivity {

    Button btnScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        btnScanner = findViewById(R.id.btn_scan);
        btnScanner.setOnClickListener(v->
        {
            scanCode();
        });
    }
    private void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(Capture.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher  = registerForActivityResult(new ScanContract(),result ->
    {
       if(result.getContents()!=null)
       {
           AlertDialog.Builder builder = new AlertDialog.Builder(BarcodeScannerActivity.this);
           builder.setTitle("Result");
           builder.setMessage(result.getContents());
           builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   dialogInterface.dismiss();
               }
           }).show();
       }else{
           Toast.makeText(getApplicationContext(),"You didn't scan anything",Toast.LENGTH_SHORT).show();
       }
    });
}