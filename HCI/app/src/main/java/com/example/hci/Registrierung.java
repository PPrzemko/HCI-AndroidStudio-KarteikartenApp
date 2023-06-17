package com.example.hci;

import com.example.hci.repositories.UserRepository;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;




import com.example.hci.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


public class Registrierung extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);


        Button erstellen = findViewById(R.id.erstellenButton);
        erstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                EditText editText;

                editText = findViewById(R.id.NameEingabe);
                String name = editText.toString();

                editText = findViewById(R.id.EingabeEmail);
                String email = editText.toString();

                editText = findViewById(R.id.EingabePasswort);
                String password = editText.toString();

                User user = new User(name, email, password);
                UserRepository.getInstance().save(user);

                System.out.print("User hinzugefuegt");
                String pathFile = "Macintosh HD/data.json";

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
                FileWriter writer = null;
                try {
                     writer = new FileWriter(pathFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(jsonArray.toString());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }



                startActivity(i);
            }
        });
    }
}