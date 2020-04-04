package com.example.eva2_8_sqlite3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModificationActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText edName, edEmail;
    Button btnUpdate, btnDelete;
    String name, email;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);

        //We receive the putextra
        Bundle bundle = getIntent().getExtras();
        final int uid = (int) bundle.get("user_id");
        final String uname = (String) bundle.get("user_name");
        final String uemail = (String) bundle.get("user_email");

        //Inputs from the user
        edName = (EditText) findViewById(R.id.edName);
        edEmail = (EditText) findViewById(R.id.edEmail);

        //Button actions from the user
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        //Textview from view
        user = findViewById(R.id.txtTtile);
        user.setText("Modifying user: " + uname);

        //Set the data with the put extra
        edName.setText(uname);
        edEmail.setText(uemail);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db = ModificationActivity.this.openOrCreateDatabase("DB", MODE_PRIVATE, null);

                    // here you do something with your database ...
                    deleteData(uid);  //Send id for delete

                    db.close();

                } catch (SQLiteException e) {
                    Toast.makeText(ModificationActivity.this, "ERROR " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                email = edEmail.getText().toString();

                try {
                    db = ModificationActivity.this.openOrCreateDatabase("DB", MODE_PRIVATE, null);

                    // here you do something with your database ...
                    updateData(uid, name, email);  //Send id for delete

                    db.close();

                } catch (SQLiteException e) {
                    Toast.makeText(ModificationActivity.this, "ERROR " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void deleteData(int id) {
        String msg = "SUCCESSFULLY DELETED";
        db.beginTransaction();
        try {
            //perform your database operations here ...
            int row = db.delete("PEOPLE", "recID=" + id, null);
            db.setTransactionSuccessful(); //commit your changes
        } catch (SQLiteException e) {
            //report problem
            msg = "ERROR: " + e.getMessage();
        } finally {
            db.endTransaction();
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            edName.setText("");
            edEmail.setText("");
            Intent intent = new Intent(this, RecoveredUsers.class);
            startActivity(intent);
        }
    }

    private void updateData(int id, String name, String email) {
        String msg = "USER " + name + " SUCCESSFULLY  UPDATED";
        db.beginTransaction();
        try {
            //perform your database operations here ...
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("email", email);
            int row = db.update("PEOPLE", contentValues, "recID=" + id, null);
            db.setTransactionSuccessful(); //commit your changes
        } catch (SQLiteException e) {
            //report problem
            msg = "ERROR: " + e.getMessage();
        } finally {
            db.endTransaction();
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            edName.setText("");
            edEmail.setText("");
            Intent intent = new Intent(this, RecoveredUsers.class);
            startActivity(intent);
        }
    }

}
