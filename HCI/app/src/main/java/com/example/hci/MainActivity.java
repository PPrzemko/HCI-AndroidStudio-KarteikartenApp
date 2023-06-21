package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.CurrentData;
import com.example.hci.usecase.Jsonmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private CurrentData currentData = CurrentData.getInstance();
    private Jsonmanager jsonmanager = Jsonmanager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button LoginButton = findViewById(R.id.Login);

        jsonmanager.readFromJson(getApplicationContext());


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean exitstiert = false;
                EditText editText;

                editText = findViewById(R.id.editTextText);
                String nutzername = editText.getText().toString();

                editText = findViewById(R.id.editTextTextPassword);
                String password = editText.getText().toString();

                for (Map.Entry<UUID, User> entry : UserRepository.getInstance().getUsersList().entrySet()) {
                    String neuerName = entry.getValue().getUsername().toString();
                    String nerPasswort = entry.getValue().getPassword().toString();
                    if (neuerName.equals(nutzername) && nerPasswort.equals(password)) {
                        currentData.setUserId(entry.getKey());
                        Intent i = new Intent(getApplicationContext(), FriendfeedActivity.class);
                        startActivity(i);
                    }
                    ;
                }
                ;

            }
        });


        Button RegisterButton = findViewById(R.id.Register);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Registrierung.class);
                System.out.println("Bist drin");
                startActivity(i);
            }
        });

        Button ResetPasswordBtn = findViewById(R.id.PasswortVergessen);

        ResetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
                i.putExtra("cameFromProfile", false);
                startActivity(i);
            }
        });
    }
}