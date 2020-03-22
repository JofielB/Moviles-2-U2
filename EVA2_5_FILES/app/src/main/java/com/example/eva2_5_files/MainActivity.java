package com.example.eva2_5_files;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btnOne, btnTwo;
    String sRutaSD;
    final int PERMISO_ESCRITURA = 1000;
    final String FILE = "My file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=  PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,                     //CONTEXT
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},  //PERMISSION ASKED
                    PERMISO_ESCRITURA);                                        //ID FOR THE REQUEST
        }

        editText = findViewById(R.id.editText);
        btnOne = findViewById(R.id.button);
        btnTwo = findViewById(R.id.button2);

                                           //CREATE A MUSIC FOLDER
        sRutaSD = getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath();//Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(this,sRutaSD,Toast.LENGTH_SHORT).show();

        //READ
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //READ FROM THE EXTERNAL STORAGE, DOSENT WORK ON THE NEWER VERSION
                    FileInputStream fis = new FileInputStream(sRutaSD + "/" + FILE); //ASK FOR THE ROUTE AND THE FILE TO STORE

                    //READ FROM DE EXTERNAL STORAGE, WORKS IN THE NEWER AND OLDER VERSIONS
                    File file = new File(getExternalFilesDir(null),FILE);
                    FileInputStream fistream = new FileInputStream(file);

                    //READ FROM THE INTERNAL STORAGE
                    InputStream is = openFileInput(FILE);


                    InputStreamReader isr = new InputStreamReader(fistream); // "FIS": STORE IN THE EXTERNAL STORAGE  / "IS": STORE IN TE LOCAL STORAGE  / "fistream" = SD STORAGE FOR NEW VERSIONS
                    BufferedReader br = new BufferedReader(isr);
                    String sLine;
                    while ((sLine=br.readLine())!=null){
                        editText.append(sLine + "\n");
                    }
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //WRITE
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sText = editText.getText().toString();
                try {
                    FileOutputStream fos = new FileOutputStream(sRutaSD + "/" + FILE);
                    OutputStream os = openFileOutput(FILE,0);


                    File file = new File(getExternalFilesDir(null),FILE);
                    FileOutputStream fostream = new FileOutputStream(file);

                    OutputStreamWriter osw = new OutputStreamWriter(fostream);
                    BufferedWriter bw = new BufferedWriter(osw);
                    bw.write(sText);
                    bw.close();
                }catch (Exception e){

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //HERE YOU CODE DE BEHAVIOR IN CASE THE USER DINT GRANT THE PERMISSION
    }
}
