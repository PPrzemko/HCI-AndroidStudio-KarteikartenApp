package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;

import com.example.hci.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        navbar();

        Button aendern = findViewById(R.id.button3);
        aendern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(i);
            }
        });


    }
    public void navbar() {
        ImageButton a = findViewById(R.id.btnActivity);
        ImageButton b = findViewById(R.id.btnFriendslist);
        ImageButton c = findViewById(R.id.btnStack);
        ImageButton d = findViewById(R.id.btnProfile);
        ImageButton e = findViewById(R.id.btnLogout);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FriendfeedActivity.class);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FriendlistActivity.class);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);
                startActivity(i);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile.class);
                startActivity(i);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO ADD LOGOUT FUNCTIONALITY
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                try {
                    File file = new File("/Users/mzerai/StudioProjects/hci-ss2023-Gruppe1-Roggenkamp_Stockbauer_Westermeier_Zerai-Micael/HCI");
                    objectMapper.writeValue(file, UserRepository.getInstance().getUsersList());
                    System.out.println("HashMap wurde in data.json gespeichert.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(i);
            }
        });
    }
}