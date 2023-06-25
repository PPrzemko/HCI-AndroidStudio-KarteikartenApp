package com.example.hci.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

import android.util.Log;

public class Deck {

    private UUID deckId;
    private String name;
    private ArrayList<FlashCard> flashCards;

    public Deck(String name){
        this.name=name;
        flashCards=new ArrayList<>();
        deckId=UUID.randomUUID();
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
        returnList.add(new FlashCard("zusatz", "zusat"));
        return returnList;
    }
}
