package com.example.hci;

import com.example.hci.repositories.UserRepository;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hci.model.User;


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
                UserRepository.getInstance().getUsersById().put(user.getUserId(),user);





                startActivity(i);
            }
        });
    }
}