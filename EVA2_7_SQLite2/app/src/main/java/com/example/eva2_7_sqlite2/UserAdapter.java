package com.example.eva2_7_sqlite2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User>{
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

        if(convertView == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            convertView = layoutInflater.inflate(resource,parent,false);
        }

        txtID = convertView.findViewById(R.id.listID);
        txtName = convertView.findViewById(R.id.listName);
        txtEmail = convertView.findViewById(R.id.listEmail);

        txtID.setText(""+users.get(position).getId());
        txtName.setText(users.get(position).getName());
        txtEmail.setText(users.get(position).getEmail());

        return convertView;
    }
}
