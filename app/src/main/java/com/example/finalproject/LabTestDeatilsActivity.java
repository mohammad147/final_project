package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDeatilsActivity extends AppCompatActivity {
TextView tv1,tv32;
EditText tv2;
Button btnback,btnaddtocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_deatils);
        tv1 = findViewById(R.id.textView_title2);
        tv32 = findViewById(R.id.detailtxt);
        tv2 = findViewById(R.id.edittext);
        tv2.setKeyListener(null);
        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("text1"));
        tv2.setText(intent.getStringExtra("text2"));
        tv32.setText("total cost" + intent.getStringExtra("text3") +"jod");
        btnback = findViewById(R.id.back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( LabTestDeatilsActivity.this,LabTestActivity.class));
            }
        });
        btnaddtocart = findViewById(R.id.btngotocart);
        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tv1.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "product already added", Toast.LENGTH_SHORT).show();
                }else
                {
                    db.addCart(username,product,price,"lab");
                }
            }
        });
    }
}