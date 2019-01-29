package com.example.csu.newdiary;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Diary extends AppCompatActivity {
    Button ShowE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        ShowE = findViewById(R.id.ShowE);


        ShowE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(Diary.this,ShowEntries.class);
                startActivity(s);
            }
        });
    }
}
