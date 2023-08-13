package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String [][] packages = {
        {"Advil ","","","","2"},
        {"Tylenol","","","","1"},
        {"Benadryl","","","","1"},
        {"Zyrtec","","","","2"},
        {"Prilosec","","","","10"},
        {"Lipitor","","","","30"},
        {"Levothyroxine","","","","10"},
        {"Insulin","","","","100"},
        {"Flu vaccine","","","","20"},
    };
    private String [] packages_details ={
        "A pain reliever and fever reducer. It is used to treat headaches, muscle aches, arthritis pain, and fever. \n"+
                " It can also be used to relieve pain and inflammation after surgery or dental procedures.",
                "Another pain reliever and fever reducer. It is similar to ibuprofen, but it is less likely to cause stomach upset.\n" +
                        " Tylenol is also used to treat colds and flu symptoms.",
            "An antihistamine that is used to treat allergies, hay fever, and hives. It can also be used to relieve itching and to help you sleep.",
            "Another antihistamine that is used to treat allergies, hay fever, and hives. It is less likely to cause drowsiness than Benadryl",
            "A proton pump inhibitor that is used to treat heartburn, acid reflux, and ulcers. It works by reducing the amount of acid produced in the stomach",
            "A statin that is used to lower cholesterol. It works by blocking the production of cholesterol in the liver",
            "A synthetic thyroid hormone that is used to treat hypothyroidism. It works by replacing the thyroid hormone that the body is not producing enough of.",
            " A hormone that is produced by the pancreas. It is used to treat diabetes by helping the body to use glucose for energy",
            " A vaccine that is used to protect against the flu. It is given every year to help prevent the flu virus from spreading"
    };
    HashMap<String,String> item;
    ArrayList List;
    SimpleAdapter sa;
    ListView lst;
    Button btnback,btngotocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        lst = findViewById(R.id.edittext);
        btnback = findViewById(R.id.back);
        btngotocart = findViewById(R.id.goToCart);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, homeActivity.class));
            }
        });
        btngotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });
        List = new ArrayList();
        for(int i =0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","cost:" + packages[i][4] +"jod");
            List.add(item);
        }
        sa = new SimpleAdapter(this,List,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.lineb,R.id.linec,R.id.lined,R.id.linee});
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                intent.putExtra("text1",packages[i][0]);
                intent.putExtra("text2",packages_details[i]);
                intent.putExtra("text3",packages[i][4]);
                startActivity(intent);
            }
        });

    }
}