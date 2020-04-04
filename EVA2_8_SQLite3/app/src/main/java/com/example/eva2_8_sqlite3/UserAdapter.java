package com.example.eva2_8_sqlite3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    Context context;
    int resource;
    ArrayList<User> users;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        users = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView txtID, txtName, txtEmail;

        //We store the data in the Usermodel
        final User user = users.get(position);
        final int uid = user.getId();
        final String uname = user.getName();
        final String uemail = user.getEmail();

        if (convertView == null) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            convertView = layoutInflater.inflate(resource, parent, false);
        }

        txtID = convertView.findViewById(R.id.listID);
        txtName = convertView.findViewById(R.id.listName);
        txtEmail = convertView.findViewById(R.id.listEmail);

        txtID.setText("" + uid);
        txtName.setText("" + uname);
        txtEmail.setText("" + uemail);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ModificationActivity.class);
                //We send the data with putextras
                intent.putExtra("user_id", uid); //Id from database
                intent.putExtra("user_name", uname);
                intent.putExtra("user_email", uemail);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
