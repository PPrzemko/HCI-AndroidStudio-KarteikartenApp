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
import android.widget.SearchView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.repositories.DeckRepository;
import com.example.hci.usecase.CurrentData;
import com.example.hci.usecase.LearningSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
        UUID id = currentData.getDeckId();
        final Deck currentDeck = deckRepository.findById(id);

        recyclerView = findViewById(R.id.recyclerViewStackPreview);
        TextView titelTv = findViewById(R.id.stackPreviewTitle);
        titelTv.setText(currentDeck.getName());


        recyclerView = findViewById(R.id.recyclerViewStackPreview);
        ArrayList<FlashCard> test4 = null;
        try {
            test4 = currentDeck.getFlashCards();
        } catch (Exception e) {
            Log.d("Error:", "onCreate: " + e);
        }

        customViewAdapter = new StackPreviewCustomViewAdapter(test4, getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customViewAdapter);


        Button learnStackButton = findViewById(R.id.registerButton);
        learnStackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LearningSession learningSession = new LearningSession(currentDeck.clone());
                currentData.setLearningSession(learningSession);

                Intent i = new Intent(getApplicationContext(), card.class);
                startActivity(i);
            }
        });

        SearchView searchViews = findViewById(R.id.SearchView2);
        searchViews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                ArrayList<FlashCard> test = currentDeck.searchForCardByQuery(newText);
                customViewAdapter.setItemList(test);
                return false;
            }
        });


        ImageButton backButton = findViewById(R.id.backbtn2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);
                startActivity(i);
            }
        });
    }



}