package com.example.hci.usecase;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;

import java.util.ArrayList;
import java.util.UUID;

public class LearningSession {

    private ArrayList<FlashCard> pendingFlashcards;
    int counter;

    public LearningSession(Deck deck){
        this.pendingFlashcards=deck.getFlashCards();
    }

    public void showFlashCardAgainAtEnd(FlashCard card){
        pendingFlashcards.add(card);
    }

    public FlashCard getFlashcard(){
        return pendingFlashcards.get(counter);
    }

    public void incrementCounter(){
        counter++;
    }

    public void decrementCounter(){
        counter--;
    }

    public void resetCounter(){
        counter=0;
    }

    public boolean checkForLastCard(){
        boolean returnValue = false;
        if(counter>= pendingFlashcards.size()){
            returnValue=true;
        }
        return returnValue;
    }
}
