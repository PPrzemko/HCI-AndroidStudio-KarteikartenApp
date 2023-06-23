package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.usecase.CurrentData;
import com.example.hci.usecase.LearningSession;

public class card extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        CurrentData currentData = CurrentData.getInstance();


        //test stuff
        Deck testDeck = new Deck("test");

        testDeck.addFlashCard(new FlashCard("vorne1", "hinten1"));
        testDeck.addFlashCard(new FlashCard("vorne2", "hinten2"));

        LearningSession testLearningSession = new LearningSession(testDeck);
        currentData.setLearningSession(testLearningSession);
        //test stuff ende

        LearningSession learningSession = currentData.getLearningSession();

        FlashCard currentCard = learningSession.getFlashcard();

        TextView front = findViewById(R.id.cardtextfront);
        front.setText(currentCard.getFront());

        TextView back = findViewById(R.id.cardtextback);
        back.setText(currentCard.getBack());

        back.setVisibility(View.INVISIBLE);

        Button backgroundButton = findViewById(R.id.buttonBackgroundClickable);
        backgroundButton.setBackgroundColor(Color.TRANSPARENT);

        ImageButton wrongButton = findViewById(R.id.wrongBtn);
        ImageButton correctButton = findViewById(R.id.correctBtn);
        ImageButton backButton = findViewById(R.id.backbtn);


        backgroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongButton.setVisibility(View.VISIBLE);
                correctButton.setVisibility(View.VISIBLE);

                back.setVisibility(View.VISIBLE);

                backgroundButton.setVisibility(View.GONE);
            }
        });

        wrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), card.class);
                learningSession.showFlashCardAgainAtEnd(currentCard);
                learningSession.incrementCounter();
                if (learningSession.checkForLastCard()) {
                    i = new Intent(getApplicationContext(), stackpreview.class);
                    back.setVisibility(View.INVISIBLE);
                    wrongButton.setVisibility(View.INVISIBLE);
                    correctButton.setVisibility(View.INVISIBLE);
                    front.setText("Herzlichen Glückwunsch, du hast die letzte Karte dieses Durchgangs gelernt");
                } else {
                    startActivity(i);
                }
            }
        });

        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), card.class);
                learningSession.incrementCounter();
                if (learningSession.checkForLastCard()) {
                    i = new Intent(getApplicationContext(), stackpreview.class);
                    back.setVisibility(View.INVISIBLE);
                    wrongButton.setVisibility(View.INVISIBLE);
                    correctButton.setVisibility(View.INVISIBLE);
                    front.setText("Herzlichen Glückwunsch, du hast die letzte Karte dieses Durchgangs gelernt");
                } else {
                    startActivity(i);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), stackpreview.class);
                startActivity(i);
            }
        });
    }
}