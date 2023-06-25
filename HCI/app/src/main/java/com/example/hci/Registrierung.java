package com.example.hci;

import com.example.hci.repositories.UserRepository;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hci.model.User;
import com.example.hci.usecase.CurrentData;
import com.example.hci.usecase.Jsonmanager;

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


public class Registrierung extends AppCompatActivity {

    private UserRepository userRepository = UserRepository.getInstance();
    private CurrentData currentData = CurrentData.getInstance();

    private Jsonmanager jsonmanager = Jsonmanager.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);

        TextView errorNoUser = findViewById(R.id.errorNoUserReg);
        errorNoUser.setVisibility(View.INVISIBLE);
        errorNoUser.setText("Dieser Benutzername ist bereits vergeben");

        Button erstellen = findViewById(R.id.erstellenButton);
        erstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //neuen User speicher
                //zu Activitäten view wechseln -> user name übergeben
                //json einlesen

                Intent i = new Intent(getApplicationContext(), FriendfeedActivity.class);
                EditText editText;

                editText = findViewById(R.id.NameEingabe);
                String name = editText.getText().toString();

                editText = findViewById(R.id.EingabeEmail);
                String email = editText.getText().toString();

                editText = findViewById(R.id.EingabePasswort);
                String password = editText.getText().toString();

                if (userRepository.getUsersList().size() == 0) {
                    User user = new User(name, email, password);
                    userRepository.save(user);

                    System.out.print("User hinzugefuegt");

                    //ERSTELLEN EINER JSON + LESEN EINER JSON
                    jsonmanager.writeToJson(getApplicationContext());

                    currentData.setUserId(user.getUserId());
                    startActivity(i);
                }

                for (Map.Entry<UUID, User> entry : userRepository.getUsersList().entrySet()) {
                    if (entry.getValue().getUsername().equals(name)) {
                        Log.d("TEST", "Gibt den User schon");
                        //TODO: Fehlermeldung
                        errorNoUser.setVisibility(View.VISIBLE);
                    } else {
                        User user = new User(name, email, password);
                        userRepository.save(user);

                        jsonmanager.writeToJson(getApplicationContext());

                        currentData.setUserId(user.getUserId());
                        startActivity(i);
                    }

                }

            }
        });
    }
}