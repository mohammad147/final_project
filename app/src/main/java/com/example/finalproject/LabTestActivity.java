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

public class LabTestActivity extends AppCompatActivity {
 private String [][] pakages = {
         {"pakage 1 : full body checkup","","","","999"},
         {"pakage 2 : blood glucose fasting","","","","299"},
         {"pakage 3 : covid 19 anitybody ","","","","899"},
         {"pakage 4 : thyroid check","","","","499"},
         {"pakage 5 : imunity check","","","","699"},
 };
 private String[] pakage_details = {
         "blood glucose fasting \n "+
                 "complete hemogram\n"+
                 "hbA1c\n"+
                 "iron studies\n"+
         "kideney funtion test\n"+
                 "LDH lactade dehydrogenase serum"+
                 "lipid profile\n"+
                 "liver function Test",
         "blood glucose fasting ",
         "covid 19 anitybody  ",
         "thyroid profile",
         "imunity check vaitamins"
 };
 HashMap<String,String> item;
 ArrayList list ;
 SimpleAdapter sa;
 Button btngotocart1,bbtn;
 ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btngotocart1 = findViewById(R.id.btngotocart);
        bbtn = findViewById(R.id.back);
        listView=findViewById(R.id.edittext);


        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(LabTestActivity.this,homeActivity.class));
            }
        });
        list = new ArrayList<>();
        for (int i=0;i<pakages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",pakages[i][0]);
            item.put("line2",pakages[i][1]);
            item.put("line3",pakages[i][2]);
            item.put("line4",pakages[i][3]);
            item.put("line5","fees:" + pakages[i][4] +"jod");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.lineb,R.id.linec,R.id.lined,R.id.linee}
        );
        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LabTestActivity.this,LabTestDeatilsActivity.class);
                intent.putExtra("text1",pakages[i][0]);
                intent.putExtra("text2",pakage_details[i]);
                intent.putExtra("text3",pakages[i][4]);
                startActivity(intent);
            }
        });

       btngotocart1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(LabTestActivity.this, GoToCartActivity.class));
           }
       });
    }
}