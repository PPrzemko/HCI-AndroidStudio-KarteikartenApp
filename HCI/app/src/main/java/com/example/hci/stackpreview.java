package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        Deck currentDeck = deckRepository.findById(currentData.getDeckId());
        LearningSession learningSession = new LearningSession(currentDeck); ////////////////current data learnign session setzen


        recyclerView = findViewById(R.id.recyclerView);
        cardList = new ArrayList<FlashCard>();
        cardList.add(new FlashCard("1+1", "2"));
        cardList.add(new FlashCard("10/2", "5"));
        cardList.add(new FlashCard("3*3", "9" ));
        cardList.add(new FlashCard("1+1", "2"));
        cardList.add(new FlashCard("10/2", "5"));
        cardList.add(new FlashCard("3*3", "9" ));
        cardList.add(new FlashCard("1+1", "2"));
        cardList.add(new FlashCard("10/2", "5"));
        cardList.add(new FlashCard("3*3", "9" ));

        recyclerView = findViewById(R.id.recyclerView);
        customViewAdapter = new StackPreviewCustomViewAdapter(cardList, getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customViewAdapter);











    }
}