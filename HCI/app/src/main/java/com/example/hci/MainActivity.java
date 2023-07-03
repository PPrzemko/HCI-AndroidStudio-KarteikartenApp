package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.model.User;
import com.example.hci.repositories.DeckRepository;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.CurrentData;
import com.example.hci.usecase.Jsonmanager;

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
        TextView errorNoUser = findViewById(R.id.errorNoUser);
        errorNoUser.setVisibility(View.INVISIBLE);
        errorNoUser.setText("");
        jsonmanager.readFromJson(getApplicationContext());



        //test sachen für filterfunktion
        Deck testDeck = new Deck("test");

        testDeck.addFlashCard(new FlashCard("aaaaa", "ddd"));
        testDeck.addFlashCard(new FlashCard("bbbb", "eeee"));
        testDeck.addFlashCard(new FlashCard("ccccccccccc", "fffffffff"));

        /*
        final Deck currentDeck= testDeck;
        DeckRepository deckRepository = DeckRepository.getInstance();
        deckRepository.addNewDeck(testDeck);

        currentData.setDeckId(testDeck.getDeckId());
        currentData.setCurrenedFilteredCards(testDeck.getFlashCards());
        */



        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean exitstiert = false;
                EditText editText;

                editText = findViewById(R.id.editTextFilterInput);
                String nutzername = editText.getText().toString();

                editText = findViewById(R.id.editTextTextPassword);
                String password = editText.getText().toString();
                User user = UserRepository.getInstance().checkLoginCreds(nutzername, password);
                if(user != null){
                    currentData.setUserId(user.getUserId());
                    Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);
                    startActivity(i);
                }else{
                    errorNoUser.setVisibility(View.VISIBLE);
                    errorNoUser.setText("Der Nutzername oder das Passwort ist falsch oder existiert nicht");;
                }

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
