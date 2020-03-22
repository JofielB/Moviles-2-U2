package com.example.eva2_4_files_resource;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
    }

    //Lectura de archivas mejor despues del onCreate
    @Override
    protected void onStart() {
        super.onStart();
        InputStream is = getResources().openRawResource(R.raw.texto);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String sCade;
        try {
            sCade = br.readLine();
            while (sCade!= null) {
                text.append(sCade + "\n");
                sCade = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
