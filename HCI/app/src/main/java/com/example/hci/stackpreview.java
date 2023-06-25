package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.repositories.DeckRepository;
import com.example.hci.usecase.CurrentData;
import com.example.hci.usecase.LearningSession;

import java.util.ArrayList;
import java.util.List;

public class stackpreview extends AppCompatActivity {
    List<FlashCard> cardList;
    RecyclerView recyclerView;
    StackPreviewCustomViewAdapter customViewAdapter;
    CurrentData currentData = CurrentData.getInstance();
    DeckRepository deckRepository = DeckRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stackpreview);

        final Deck currentDeck = deckRepository.findById(currentData.getDeckId());

        recyclerView = findViewById(R.id.recyclerViewStackPreview);
 /*
        //Test stuff
        Deck testDeck = new Deck("test");

        testDeck.addFlashCard(new FlashCard("vorne1", "hinten1"));
        testDeck.addFlashCard(new FlashCard("vorne2", "hinten2"));

        final Deck currentDeck= testDeck;
        //Test stuff ende
*/

        recyclerView = findViewById(R.id.recyclerViewStackPreview);
        ArrayList<FlashCard> test = null;
        try {
            test = currentDeck.getFlashCards();
        } catch (Exception e) {
            Log.d("Error:", "onCreate: " + e);
        }

        customViewAdapter = new StackPreviewCustomViewAdapter(test, getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customViewAdapter);


        Button learnStackButton = findViewById(R.id.registerButton);
        learnStackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LearningSession learningSession = new LearningSession(currentDeck);
                currentData.setLearningSession(learningSession);

                Intent i = new Intent(getApplicationContext(), card.class);
                startActivity(i);
            }
        });

        TextView searchInputTextEdit = findViewById(R.id.editTextFilterInput);


        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchInput = searchInputTextEdit.getText().toString();

                ArrayList<FlashCard> filteredList = currentDeck.searchForCardByQuery(searchInput);
                if (filteredList.size() != 0) {
                    currentData.setCurrenedFilteredCards(filteredList);
                }else{
                    currentData.setCurrenedFilteredCards(currentDeck.getFlashCards());
                }

                Intent i = new Intent(getApplicationContext(), stackpreview.class);
                startActivity(i);
            }
        });
    }
}