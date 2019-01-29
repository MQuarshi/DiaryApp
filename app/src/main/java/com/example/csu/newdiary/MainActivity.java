package com.example.csu.newdiary;


import android.annotation.SuppressLint;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText name, pword, confirm;
    Button register,login1;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        pword = findViewById(R.id.pword);
        name = findViewById(R.id.name);
        confirm = findViewById(R.id.confirm);
        login1 = findViewById(R.id.login);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String password = pword.getText().toString();
                String confirmation = confirm.getText().toString();
                if(username.equals("")||password.equals("")||confirmation.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields ARE EMPTY",Toast.LENGTH_SHORT).show();

                }
                else{
                    if (password.equals(confirmation)){
                        Boolean chkusername = db.chkusername(username);
                        if(chkusername==true){
                            Boolean insert = db.insert(username,password);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Username already exists.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

