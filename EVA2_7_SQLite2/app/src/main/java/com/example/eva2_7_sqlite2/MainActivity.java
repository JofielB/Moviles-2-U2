package com.example.eva2_7_sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText edName, edEmail;
    Button btnCreate, btnRecover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inputs from the user
        edName = (EditText) findViewById(R.id.edName);
        edEmail = (EditText) findViewById(R.id.edEmail);

        //Button actions from the user
        btnCreate = findViewById(R.id.btnCreate);
        btnRecover = findViewById(R.id.btnRecover);

        //Create the user in the DB
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db = MainActivity.this.openOrCreateDatabase("DB",MODE_PRIVATE,null);

                    // here you do something with your database ...
                    createTable(edName.getText().toString(),edEmail.getText().toString());

                    db.close();

                } catch (SQLiteException e) {
                    Toast.makeText(MainActivity.this,"ERROR " + e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });

        //Launch RecoveredUsers Activity
        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecoveredUsers.class);
                startActivity(intent);
            }
        });
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
            edName.setText("");
            edEmail.setText("");
        }

    }

}
