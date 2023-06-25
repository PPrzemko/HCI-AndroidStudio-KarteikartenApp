package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.example.hci.model.Deck;
import com.example.hci.model.User;
import com.example.hci.repositories.DeckRepository;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.CurrentData;


public class create_new_deckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_deck);

        CurrentData currentData = CurrentData.getInstance();
        DeckRepository deckRepository = DeckRepository.getInstance();
        UserRepository userRepository = UserRepository.getInstance();

        User currentUser = userRepository.findById(currentData.getUserId());

        TextView deckNameTv = findViewById(R.id.stapelnameNewStack);
        String deckName = deckNameTv.toString();


        Button deckErstellenButton = findViewById(R.id.erstellenButtonNewStack);

        deckErstellenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);

                Deck newDeck = new Deck(deckName);
                deckRepository.addNewDeck(newDeck);
                currentUser.addOwnDeck(newDeck);

                startActivity(i);
            }
        });
    }
}