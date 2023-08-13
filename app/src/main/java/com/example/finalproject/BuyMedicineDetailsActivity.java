package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {
TextView packagename,totalcost;
EditText Details;
Button backbtn,cartbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        packagename = findViewById(R.id.textView_title2);
        totalcost = findViewById(R.id.detailtxt);
        Details = findViewById(R.id.edittext);
        Details.setKeyListener(null);
        backbtn = findViewById(R.id.back);
        cartbtn = findViewById(R.id.btngotocart);

        Intent intent = getIntent();
        packagename.setText(intent.getStringExtra("text1"));
        Details.setText(intent.getStringExtra("text2"));
        totalcost.setText("total coast :"+intent.getStringExtra("text3")+ "jod");
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = packagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "product already added", Toast.LENGTH_SHORT).show();
                }else
                {
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(), "Record Inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }

            }
        });
    }
}