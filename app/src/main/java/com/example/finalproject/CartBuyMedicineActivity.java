package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList list ;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private String [][] packeges = {};
    Button datebutton1,timeButton,btncheckout,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);
        tvTotal = findViewById(R.id.totalCosttxt);
        datebutton1 =findViewById(R.id.buttonDate1);
        btncheckout =findViewById(R.id.btngotocart);
        lst = findViewById(R.id.edittext);
        btnback = findViewById(R.id.back);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Database db = new Database(getApplicationContext(),"healthcare",null,1);
        float totalAmount =0;
        ArrayList dbData = db.getcartdata(username,"medicine");
        //Toast.makeText(getApplicationContext(), ""+dbData, Toast.LENGTH_LONG).show();
        packeges = new String[dbData.size()][];
        for(int i=0; i<packeges.length ; i++){
            packeges[i] = new String[5];
        }
        for(int i=0 ; i<dbData.size();i++){
            String arrdata = dbData.get(i).toString();
            String[] strData = arrdata.split(java.util.regex.Pattern.quote("$"));
            packeges[i][0] = strData[0];
            packeges[i][4]= "cost : "+ strData[1] + "jod";
            totalAmount = totalAmount+Float.parseFloat(strData[1]);
        }
        tvTotal.setText("total Cost :"+totalAmount);
        list = new ArrayList();
        for(int i =0;i<packeges.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packeges[i][0]);
            item.put("line2",packeges[i][1]);
            item.put("line3",packeges[i][2]);
            item.put("line4",packeges[i][3]);
            item.put("line5",packeges[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.lineb,R.id.linec,R.id.lined,R.id.linee}
        );

        lst.setAdapter(sa);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartBuyMedicineActivity.this,BuyMedicineActivity.class));
            }
        });
        initDataPicker();
        datebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartBuyMedicineActivity.this, BuyMedicineBookActivity.class);
                intent.putExtra("price",tvTotal.getText());
                intent.putExtra("date",datebutton1.getText());
                startActivity(intent);
            }
        });
    }
    private void initDataPicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                datebutton1.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);


    }
}