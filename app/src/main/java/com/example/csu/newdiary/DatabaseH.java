package com.example.csu.newdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class DatabaseH extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "diaryApp.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseH(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table diary(" +
                "dt datetime null, " +
                "title text null, " +
                "content text null);";
        Log.d("Diary", "onCreate: " + sql);
        db.execSQL(sql);


    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {
        // TODO Auto-generated method stub
    }
}