package com.example.hci.usecase;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;

import java.util.ArrayList;
import java.util.UUID;

public class LearningSession {

    private ArrayList<FlashCard> pendingFlashcards;

    public LearningSession(Deck deck){
        this.pendingFlashcards=deck.getFlashCards();
    }

    public void showFlashCardAgainAtEnd(FlashCard card){
        pendingFlashcards.add(card);
    }

}
