package com.example.eva2_8_sqlite3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecoveredUsers extends AppCompatActivity {
    SQLiteDatabase db;
    ArrayList<User> users = new ArrayList<>();


    ListView userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovered_users);
        Intent intent = new Intent(this, ModificationActivity.class);

        try {
            db = RecoveredUsers.this.openOrCreateDatabase("DB",MODE_PRIVATE,null);

            // here you do something with your database ...
           retriveData();

            db.close();

        } catch (SQLiteException e) {
            Toast.makeText(RecoveredUsers.this,"ERROR " + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        userList = findViewById(R.id.listUsers);
        userList.setAdapter(new UserAdapter(this,R.layout.layout_users,users));

    }

    private void retriveData(){
        String sql = "select * from PEOPLE";
        Cursor c1 = db.rawQuery(sql, null);
        c1.moveToPosition(-1);
        while ( c1.moveToNext() ){
            int recId = c1.getInt(0);
            String name = c1.getString(1);
            String email = c1.getString(2); //c1.getString(c1.getColumnIndex("phone"));
            // do something with the record here...
            users.add(new User(recId,name,email));
        }
    }

}
