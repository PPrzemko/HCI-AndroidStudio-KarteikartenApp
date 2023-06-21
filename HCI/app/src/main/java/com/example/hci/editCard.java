package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.model.User;
import com.example.hci.repositories.DeckRepository;
import com.example.hci.usecase.CurrentData;

public class editCard extends AppCompatActivity {

    CurrentData currentData = CurrentData.getInstance();
    DeckRepository deckRepository = DeckRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);


        // https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list
        Spinner dropdown = findViewById(R.id.spinnerChoseDeck);
        String[] items = new String[]{"Chemie", "Mathe", "Deutsch"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Deck currentDeck = deckRepository.findById(currentData.getDeckId());


        Button backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);
                startActivity(i);
            }
        });


        Button saveButton = findViewById(R.id.buttonBack);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), editCard.class);

                TextView frontTextTextView = findViewById(R.id.CardFrontText);
                String frontText = frontTextTextView.getText().toString();
                TextView backTextTextView = findViewById(R.id.cardRearText);
                String backText = backTextTextView.getText().toString();

                FlashCard newFlashcard = new FlashCard(frontText, backText);

                currentDeck.addFlashCard(newFlashcard);

                startActivity(i);
            }
        });
    }
}