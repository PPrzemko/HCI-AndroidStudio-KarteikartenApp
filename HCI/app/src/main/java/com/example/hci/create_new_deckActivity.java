package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.hci.model.Deck;
import com.example.hci.repositories.UserRepository;
import com.example.hci.model.User;

import com.example.hci.usecase.CurrentData;


public class create_new_deckActivity extends AppCompatActivity {

    private UserRepository userRepository = UserRepository.getInstance();
    private CurrentData currentData = CurrentData.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_deck);

        //****BUTTON Deck erstellen***

        Button deckErstellen = findViewById(R.id.Erstellen);

        deckErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText;

                editText = findViewById(R.id.Stapelname);
                String stackName = editText.getText().toString();
                //NEUES DECK ERSTELLEN
                Deck neuesDeck = new Deck(stackName);

                //USER FINDEN
                User momentanerUser = userRepository.findById(currentData.getUserId());

                //DECK ZU DEN STAPELN HINZUFÜGEN
                momentanerUser.addOwnDeck(neuesDeck);

                Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);
                startActivity(i);
            }
        });

        //**BUTTON: Zurück**

        Button zureck = findViewById(R.id.back2);

        zureck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);
                startActivity(i);
            }
        });


    }

}