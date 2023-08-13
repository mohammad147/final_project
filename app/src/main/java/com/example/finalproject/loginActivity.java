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

public class loginActivity extends AppCompatActivity {
EditText usernme,password;
Button btn;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernme = findViewById(R.id.editTextFullname);
        password = findViewById(R.id.editTextcontact);
        btn = findViewById(R.id.login_btn);
        tv= findViewById(R.id.textView3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = usernme.getText().toString();
                String Password = password.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(Username.length()==0|| Password.length()==0){
                    Toast.makeText(getApplicationContext(),"login unsuccessful",Toast.LENGTH_SHORT).show();
                }else {
                    if(db.login(Username,Password)==1){
                        Toast.makeText(getApplicationContext(), "login successful", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",Username);
                        editor.apply();
                        startActivity(new Intent(loginActivity.this,homeActivity.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "invlid username and password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this,RegisterActivity.class));
            }
        });
    }
}