package com.example.eva2_6_sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtMsg = (TextView) findViewById(R.id.txtMsg);

        try {
            db = this.openOrCreateDatabase("db",MODE_PRIVATE,null);
            // here you do something with your database ...
            db.close();
            txtMsg.append(db.getPath());
            txtMsg.append("\nDB created");
        } catch (SQLiteException e) {
            txtMsg.append("\nERROR " + e.getMessage());
        }
    }
}



