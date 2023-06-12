package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button h=findViewById(R.id.Login);

        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FriendfeedActivity.class);
                startActivity(i);
            }
        });


        Button r = findViewById(R.id.Register);

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Registrierung.class);
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