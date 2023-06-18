package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button h=findViewById(R.id.Login);

        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String fileName = "data.txt";
                    String path = getApplicationContext().getExternalFilesDir(null).getPath();
                    String filePath = path + "/" + fileName;
                    File file = new File(filePath);
                    FileInputStream fis = new FileInputStream(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append("\n");
                    }
                    reader.close();
                    fis.close();


                    String fileContents = stringBuilder.toString();

                    Log.d("HIER IST DER STRING",fileContents);
                    // Use the fileContents variable which contains the contents of the file
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getApplicationContext(), FriendfeedActivity.class);
                startActivity(i);
            }
        });


        Button r = findViewById(R.id.Register);

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Registrierung.class);
                System.out.println("Bist drin");
                startActivity(i);
            }
        });

        Button passwortVergessen = findViewById(R.id.PasswortVergessen);

        passwortVergessen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(i);
            }
        });







    }
}