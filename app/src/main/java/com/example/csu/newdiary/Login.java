package com.example.csu.newdiary;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username1,pword1;
    Button loginB;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(Login.this);

        username1 = findViewById(R.id.usernameL);
        pword1 = findViewById(R.id.pwordL);
        loginB = findViewById(R.id.login);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username1.getText().toString();
                String password = pword1.getText().toString();
                Boolean chkusernamepassword = db.usernamepassword(username, password);
                if (chkusernamepassword == true) {
                    Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(Login.this, Diary.class);
                    startActivity(j);
                }else {
                    Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }
}

