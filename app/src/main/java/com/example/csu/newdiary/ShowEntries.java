package com.example.csu.newdiary;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.EditText;

import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowEntries extends AppCompatActivity {

    String[] daftar;
    String[] daftar2;
    ListView ListView01;
    RelativeLayout rl;
    protected Cursor cursor;
    DatabaseH dbcenter;
    @SuppressLint("StaticFieldLeak")
    public static ShowEntries dl;
    List<Recyclerview > rec;
    RecyclerView rv;
    MakeList ra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_entries);
        final EditText searchText = new EditText(ShowEntries.this);

        rv = findViewById(R.id.my_recycler_view);
        rl = findViewById(R.id.rl);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CreateEntry.class));
            }
        });
        dl=this;
        dbcenter = new DatabaseH(this);
        RefreshList();
        final AlertDialog dialog = new AlertDialog.Builder(ShowEntries.this)
                .setTitle("Search")
                .setMessage("Enter your search key")
                .setView(searchText)
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase db = dbcenter.getReadableDatabase();
                        cursor = db.rawQuery("SELECT * FROM diary ORDER BY dt DESC", null);
                        String query = "SELECT * FROM diary WHERE dt LIKE ? OR " +
                                "title LIKE ? OR " +
                                "content LIKE ? ORDER by dt DESC";
                        cursor = db.rawQuery(
                                query, new String[] {
                                        "%"+searchText.getText().toString()+"%",
                                        "%"+searchText.getText().toString()+"%",
                                        "%"+searchText.getText().toString()+"%"
                                });
                        RefreshCycle();
                        fetch();
                    }
                })
                .setNegativeButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RefreshList();
                    }
                }).create();

            }





    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM diary ORDER BY dt DESC", null);
        RefreshCycle();
        fetch();
    }

    public void fetch() {
        daftar = new String[cursor.getCount()];
        daftar2 = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
        }
        ListView01 = (ListView) findViewById(R.id.lv1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                Intent in =new Intent(getApplicationContext(), ShowEntryA.class);
                in.putExtra("diary", selection);
                startActivity(in);
            }
        });
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
        Toast.makeText(getApplicationContext(), "Data refreshed", Toast.LENGTH_LONG).show();
    }

    public void RefreshCycle() {
        rec = new ArrayList<>();
        StringBuffer sB = new StringBuffer();
        Recyclerview  rMec = null;
        cursor.moveToFirst();
        cursor.moveToPosition(0);
        for (int cc = 0; cc < cursor.getCount();cc++) {
            cursor.moveToPosition(cc);
            rMec= new Recyclerview ();
            String title = cursor.getString(1).toString();
            String dt = cursor.getString(0).toString();
            String content = cursor.getString(2).toString();
            rMec.setTitle(title);
            rMec.setDate(dt);
            rMec.setContent(content);
            sB.append(rMec);
            rec.add(rMec);
        }
        ra = new MakeList(rec);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(ra);
        Toast.makeText(getApplicationContext(), "Data Refreshed", Toast.LENGTH_LONG).show();
    }
}
