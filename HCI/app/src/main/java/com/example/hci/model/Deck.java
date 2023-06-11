package com.example.hci.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

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


/*
    public Set<FlashCard> findCardsByQuery(Set<String> words){ //bei shop dings schauen
        var wordsLower = words.stream()
                .map(String::toLowerCase)
                .collect(toSet());

        return shopRepository.findPredicate(shop -> shopNameContainsWord(shop, wordsLower)
    }

    private boolean shopNameContainsWord(Shop shop, Set<String> words) {
        return words.stream()
                .anyMatch(word -> shop.name().toLowerCase().contains(word));
    }
 */
}
