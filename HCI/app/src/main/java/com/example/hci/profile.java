package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.CurrentData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.UUID;

public class profile extends AppCompatActivity {

    private CurrentData currentData = CurrentData.getInstance();
    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        navbar();
/*
        TextView tvUsername = findViewById(R.id.editTextText3);
        User currentUser = userRepository.findById(currentData.getUserId());
        tvUsername.setText(currentUser.getUsername());

        TextView tvEmailadress = findViewById(R.id.editTextTextEmailAddress);
        tvEmailadress.setText(currentUser.getEmail());
*/
        Button aendern = findViewById(R.id.button3);
        aendern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(i);
            }
        });
/*
        Button saveEditButton = findViewById(R.id.buttonSaveEdit);
        saveEditButton.setOnClickListener(new View.OnClickListener() {

            EditText inputTextUsername = (EditText)findViewById(R.id.editTextText3);
            EditText inputTextEmail = (EditText)findViewById(R.id.editTextTextEmailAddress);

            @Override
            public void onClick(View view) {
                currentUser.setEmail(tvEmailadress.getText().toString());
                currentUser.setUsername(tvUsername.getText().toString());

                finish(); //soll hoffentlich die activity neu laden
            }
        });*/
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

                //ERSTELLEN EINER JSON
                File file = new File(getExternalFilesDir(null), "data.txt");


                JSONArray jsonArray = new JSONArray();

                for (Map.Entry<UUID, User> entry : UserRepository.getInstance().getUsersList().entrySet()) {
                    JSONObject aUser = new JSONObject();
                    try {
                        aUser.put("Name", entry.getValue().getUsername());
                    } catch (JSONException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        aUser.put("Email", entry.getValue().getEmail());
                    } catch (JSONException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        aUser.put("Password", entry.getValue().getPassword());
                    } catch (JSONException ex) {
                        throw new RuntimeException(ex);
                    }

                    jsonArray.put(aUser);

                }


                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                    writer.write(jsonArray.toString());
                    writer.flush();
                    writer.close();


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                UserRepository.getInstance().getUsersList().clear();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(i);
            }
        });
    }
}