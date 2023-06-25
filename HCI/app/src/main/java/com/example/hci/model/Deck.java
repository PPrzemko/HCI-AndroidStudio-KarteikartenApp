package com.example.hci.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

import android.util.Log;

import androidx.annotation.NonNull;

public class Deck implements Cloneable{

    private UUID deckId;
    private String name;
    private ArrayList<FlashCard> flashCards;

    public Deck(String name){
        this.name=name;
        flashCards=new ArrayList<>();
        deckId=UUID.randomUUID();
    }
    @NonNull
    @Override
    public Deck clone() {
        // copy constructor needded for deep copy (Needed for learning Deck (append wrong answers to end of deck))
        try {
            Deck cloned = (Deck) super.clone();
            cloned.flashCards = new ArrayList<FlashCard>();
            for (FlashCard flashCard : flashCards) {
                cloned.flashCards.add(new FlashCard((flashCard.getFront()), flashCard.getBack()));
            }

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    public String getName() {
        return name;
    }

    public ArrayList<FlashCard> getFlashCards() {
        return flashCards;
    }

    public UUID getDeckId() {
        return deckId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlashCards(ArrayList<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }

    public void addFlashCard(FlashCard newFlashCard){
        flashCards.add(newFlashCard);
    }

    public ArrayList<FlashCard> searchForCardByQuery(String input) {
        ArrayList<FlashCard> returnList = new ArrayList<FlashCard>();
        for (FlashCard flashCard : flashCards) {
            if (flashCard.getFront().toLowerCase().contains(input.toLowerCase()) || flashCard.getBack().toLowerCase().contains(input.toLowerCase())) {
                returnList.add(flashCard);
            }
        }
        return returnList;
    }
}
