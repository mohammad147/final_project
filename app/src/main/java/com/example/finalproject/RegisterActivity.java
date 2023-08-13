package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
EditText username,password,confirmpassword,email;
Button btn;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.editTextFullname);
        password = findViewById(R.id.editTextcontact);
        confirmpassword=findViewById(R.id.editTextfees);
        email=findViewById(R.id.editTextAddress);
        btn = findViewById(R.id.Register_btn);
        tv= findViewById(R.id.textView3);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,loginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Password = password.getText().toString();
                String Email = email.getText().toString();
                String confirmPas = confirmpassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(Username.length()==0|| Password.length()==0 || confirmPas.length()==0 || Email.length()==0){
                    Toast.makeText(getApplicationContext(),"Register unsuccessful",Toast.LENGTH_SHORT).show();
                }else {
                    if(Password.compareTo(confirmPas)==0){
                     if(isVlid(Password)){
                         db.Register(Username,Email,Password);
                         Toast.makeText(getApplicationContext(),"Register successful",Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(RegisterActivity.this,loginActivity.class));
                     }
                     else{
                         Toast.makeText(getApplicationContext(), "please check your password 1234 ", Toast.LENGTH_SHORT).show();
                     }
                    }else{
                    Toast.makeText(getApplicationContext(), "please check your password ", Toast.LENGTH_SHORT).show();
                }
                }
            }
        });
    }
    public static boolean isVlid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8){
            return false;
        }
        else{ for(int i=0;i<passwordhere.length();i++){
            if(Character.isLetter(passwordhere.charAt(i))){
                f1=1;
            }
        }
        for(int y=0;y<passwordhere.length();y++){
            if(Character.isDigit(passwordhere.charAt(y))){
                f2=1;
            }
        }
        for (int x=0;x<passwordhere.length();x++){
            char c = passwordhere.charAt(x);
            if(c>=33&& c<=46 || c==64){
                f3=1;
            }
        }
        }
        if(f1==1&&f2==1&&f3==1){
            return true;
        }
        return false;
    }

}