package com.example.csu.newdiary;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


    public class DatabaseHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "diary.db";
        private static final int DATABASE_VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, "Login.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table user(username text primary key,password text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists user");
        }
        //inserting in database
        public boolean insert(String username,String password) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Username", username);
            contentValues.put("Password", password);
            long ins = db.insert("user", null, contentValues);
            if (ins==-1) return false;
            else return true;
        }
        //checks to see if username exists
        public Boolean chkusername(String username) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from user where username=?", new String[]{username});
            if (cursor.getCount() > 0) return false;
            else return true;
        }
        //checking uname adn pword
        public Boolean usernamepassword(String username, String password) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from user where username=? and password=?", new String[]{username,password});
            if (cursor.getCount() > 0) return true;
            else return false;
        }

    }



