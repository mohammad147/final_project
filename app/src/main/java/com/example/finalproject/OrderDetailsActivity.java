package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private String[][] orderDetails ={};
            HashMap<String,String> item;
            ArrayList List;
            SimpleAdapter sa;
            ListView lst;
            Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btn = findViewById(R.id.back);
        lst = findViewById(R.id.edittext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailsActivity.this, homeActivity.class));
            }
        });
        Database db = new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbData = db.getOrderDetails(username);

        orderDetails = new String[dbData.size()][];
        for(int i=0 ; i<orderDetails.length;i++)
        {
            orderDetails[i]= new String[5];
            String arrData = dbData.get(i).toString();
            String[] strdata = arrData.split(java.util.regex.Pattern.quote("$"));
            orderDetails[i][0] = strdata[0];
            orderDetails[i][1] = strdata[1];
            if(strdata[7].compareTo("medicine")==0){
                orderDetails[i][3] = "del:" + strdata[4];
            }
            else {
                orderDetails[i][3] = "del:" +strdata[4] + " "+strdata[5];
            }
            orderDetails[i][2] = "Rs . "+strdata[6];
            orderDetails[i][4] = strdata[7];
        }
        List = new ArrayList();
        for(int i =0;i<orderDetails.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",orderDetails[i][0]);
            item.put("line2",orderDetails[i][1]);
            item.put("line3",orderDetails[i][2]);
            item.put("line4",orderDetails[i][3]);
            item.put("line5",orderDetails[i][4]);
            List.add(item);
        }
        sa = new SimpleAdapter(this,List,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.lineb,R.id.linec,R.id.lined,R.id.linee}
        );
        ListView lst = findViewById(R.id.edittext);
        lst.setAdapter(sa);
    }
}