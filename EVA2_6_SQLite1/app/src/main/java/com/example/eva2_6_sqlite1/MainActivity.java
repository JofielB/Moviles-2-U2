package com.example.eva2_6_sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
            //CREATE TABLED AND ADD A USER
            createTable("usuario","usuario@usuariomail.com");

            db.close();
            txtMsg.append(db.getPath());
            txtMsg.append("\nDB created");
        } catch (SQLiteException e) {
            txtMsg.append("\nERROR " + e.getMessage());
        }
    }


    private void createTable(String uName, String uEmail){
        String msg = "USER SUCEFULLY CREATED";
        db.beginTransaction();
        try {
            //perform your database operations here ...
            db.execSQL("CREATE TABLE IF NOT EXISTS PEOPLE ("
                    + " recID integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " email text ); " );
            db.execSQL( "insert into PEOPLE(name, email) values ( '"+ uName +"', '"+uEmail+"');" );
            db.setTransactionSuccessful(); //commit your changes
        }
        catch (SQLiteException e) {
            //report problem
            msg = "ERROR: " + e.getMessage();
        }
        finally {
            db.endTransaction();
            Toast.makeText(this, msg,Toast.LENGTH_LONG).show();
        }

    }
}



