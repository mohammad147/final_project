package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname,edaddress,edcontact,govcod;
    Button btnbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);
        edname = findViewById(R.id.editTextFullname);
        edaddress = findViewById(R.id.editTextAddress);
        edcontact = findViewById(R.id.editTextcontact);
        govcod = findViewById(R.id.editTextfees);
        btnbook =findViewById(R.id.bookbtn);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(), govcod.getText().toString(),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removecart(username,"lab");
                Toast.makeText(getApplicationContext(), "your booking is done succesfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BuyMedicineBookActivity.this, homeActivity.class));

            }
        });
    }
}