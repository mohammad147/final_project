package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String [][] doctor_details1 = {
            {"Doctor Name : Samih Aqaad","address : Khilda and Um Summaq ","avg waiting : 30 min ","mobile number : 0799805069 ","15"},
            {"Doctor Name : Rami ٍSalameh","address :Abdali ","avg waiting : 30 min ","mobile number : 0799119313 ","30"},
            {"Doctor Name : Hilmi Abu Alia","address : Al Jubeiha and Abu Nusair","avg waiting : 30 min ","mobile number : 0798347166 ","15"},
            {"Doctor Name : Oxymed Hbo Center","address : Al Jubeiha and Abu Nusair","avg waiting : 30 min ","mobile number :  0799747551 ","15"}
    };


    private String [][] doctor_details2 = {
        {"Doctor Name : Dieaa Hindi","address : Hashmi and Tabarbour ","avg waiting : 30 min ","mobile number : 0791560256 ","15"},
        {"Doctor Name : Naji Abuirmeleh","address : Al Jubeiha and Abu Nusair  ","avg waiting : 30 min ","mobile number : 0795933881 ","20"},
        {"Doctor Name : aseel Alqaisi","address : Khaldi Street","avg waiting : 30 min ","mobile number : 0787722171 ","5"},
        {"Doctor Name : Khloud jwainat Haddad","address : Fifth Circle","avg waiting : 30 min ","mobile number :  0797344667 ","20"}
    };
    private String [][] doctor_details3 = {
        {"Doctor Name : Manal Awad","address :  Mecca Street ","avg waiting : 45 min ","mobile number : 0799506706 ","10"},
            {"Doctor Name : Lina Alhamshari","address : Al Jubeiha and Abu Nusair  ","avg waiting : 30 min ","mobile number : 0796766354 ","10"},
            {"Doctor Name : Marwa Musleh","address : Khaldi Street","avg waiting : 30 min ","mobile number : 064633420 ","10"},
            {"Doctor Name : ahmad abu bader","address : Fifth Circle","avg waiting : 30 min ","mobile number :  0775331259 ","20"}
        };
    private String [][] doctor_details4 = {
                {"Doctor Name : waleed abu fares ","address :  Mecca Street ","avg waiting : 45 min ","mobile number : 0779668000 ","50"},
                        {"Doctor Name : waleed sholi ","address : Al Jubeiha and Abu Nusair  ","avg waiting : 30 min ","mobile number :0787177176 ","30"},
                        {"Doctor Name : Omar Mansour","address : Khaldi Street","avg waiting : 30 min ","mobile number : 0795217050 ","35"},
                        {"Doctor Name : eyad anabtawi","address : Fifth Circle","avg waiting : 30 min ","mobile number :  0795030507 ","20"}
                };

    private String [][]   doctor_details5 = {
                {"Doctor Name : Ramzi Tabbalat ","address :  Mecca Street ","avg waiting : 45 min ","mobile number : 064655562 ","70"},
                        {"Doctor Name : Nadine Hiari ","address : Al Jubeiha and Abu Nusair  ","avg waiting : 30 min ","mobile number : 0799457022 ","50"},
                        {"Doctor Name : Amr Rasheed","address : Khaldi Street","avg waiting : 30 min ","mobile number : 0775661006 ","15"},
                        {"Doctor Name : Ahmad EL-Harasis","address : Fifth Circle","avg waiting : 30 min ","mobile number :  065924343 ","40"}
                };

        TextView tv;
Button back;
String[][] doctor_details={};
HashMap<String,String> item;
ArrayList list ;
SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv = findViewById(R.id.textView_title);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("family Physicain")==0){
            doctor_details=doctor_details1;
        }
        else if(title.compareTo("dietician")==0){
            doctor_details=doctor_details2;
        }
        else if(title.compareTo("dentist")==0){
            doctor_details=doctor_details3;
        }
        else if(title.compareTo("surgeon")==0){
            doctor_details=doctor_details4;
        }
        else{
            doctor_details=doctor_details5;
        }
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
        list = new ArrayList();
        for(int i =0;i<doctor_details.length;i++){
item = new HashMap<String,String>();
item.put("line1",doctor_details[i][0]);
item.put("line2",doctor_details[i][1]);
item.put("line3",doctor_details[i][2]);
item.put("line4",doctor_details[i][3]);
item.put("line5","fees:" + doctor_details[i][4] +"jod");
list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.lineb,R.id.linec,R.id.lined,R.id.linee}
                );
        ListView lst = findViewById(R.id.edittext);
        lst.setAdapter(sa);

 lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
     @Override
     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
         Intent intent = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
         intent.putExtra("text1",title);
         intent.putExtra("text2",doctor_details[i][0]);
         intent.putExtra("text3",doctor_details[i][1]);
         intent.putExtra("text4",doctor_details[i][2]);
         intent.putExtra("text5",doctor_details[i][3]);
         intent.putExtra("text6",doctor_details[i][4]);
         startActivity(intent);

     }
 });
    }

}