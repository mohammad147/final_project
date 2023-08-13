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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
TextView tv;

private DatePickerDialog datePickerDialog;
private TimePickerDialog timePickerDialog;
private Button databutton,timeButton,btnbook,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        tv = findViewById(R.id.textView2);
        ed1 = findViewById(R.id.editTextFullname);
        ed2 = findViewById(R.id.editTextAddress);
        ed3 = findViewById(R.id.editTextcontact);
        ed4 = findViewById(R.id.editTextfees);
        databutton= findViewById(R.id.buttonDate);
        timeButton = findViewById(R.id.buttonDate2);
        btnbook = findViewById(R.id.Register_btn);
        btnback = findViewById(R.id.backbtn);
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);
        Intent intent = getIntent();
        String title = intent.getStringExtra("text1");
        String fulln = intent.getStringExtra("text2");
        String address = intent.getStringExtra("text3");
        String contact = intent.getStringExtra("text4");
        String number = intent.getStringExtra("text5");
        String price = intent.getStringExtra("text6");
        float pri = Float.parseFloat(price);
        String name[] = fulln.split(java.util.regex.Pattern.quote(":"));
    tv.setText(title);
    ed1.setText(fulln);
    ed2.setText(address);
    ed3.setText(contact);
    ed4.setText(number);
btnback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent1 = new Intent(BookAppointmentActivity.this,DoctorDetailsActivity.class);
        startActivity(intent1);
    }
});
btnbook.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Database db = new Database(getApplicationContext(),"heathcare",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
      if(db.checkAppointmentExists(username,name[1],address,contact,databutton.getText().toString(),timeButton.getText().toString())==1){
          Toast.makeText(getApplicationContext(), "Appointmnet already blocked", Toast.LENGTH_SHORT).show();
      }else{

         db.addOrder(username,name[1],address,contact,"0",databutton.getText().toString(),timeButton.getText().toString(),pri,"appointment");
          Toast.makeText(getApplicationContext(), "your appointment is done succsefully", Toast.LENGTH_SHORT).show();
          startActivity(new Intent(BookAppointmentActivity.this,homeActivity.class));
      }

    }
});
    initDataPicker();
    databutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            datePickerDialog.show();
        }
    });
    initTimepicker();
    timeButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            timePickerDialog.show();
        }
    });

    }
    private void initDataPicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                databutton.setText(i2+"/"+i1+"/"+i);
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
    private void initTimepicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hours,min,true);
    }
}