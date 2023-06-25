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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button LoginButton = findViewById(R.id.Login);
        try {

            //JSON DATEI LESEN
            String fileName = "data.txt";
            String path = getApplicationContext().getExternalFilesDir(null).getPath();
            String filePath = path + "/" + fileName;
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            //********IN HASH-MAP BRINGEN *****

            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader(filePath));

            for (Object o : a) {
                JSONObject person = (JSONObject) o;

                String name = (String) person.get("Name");


                String email = (String) person.get("Email");


                String password = (String) person.get("Password");

                User user = new User(name, email, password);
                UserRepository.getInstance().save(user);

            }

                    /*String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append("\n");
                    }*/

            reader.close();
            fis.close();


            //String fileContents = stringBuilder.toString();

            //Log.d("HIER IST DER STRING",fileContents);
            // Use the fileContents variable which contains the contents of the file
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

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


        Button RegisterButton = findViewById(R.id.registerButton);

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
