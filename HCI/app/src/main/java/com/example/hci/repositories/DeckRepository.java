package com.example.hci.repositories;


import com.example.hci.model.Deck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Optional;

public class DeckRepository { //TODO Singleton werden

    private final HashMap<UUID, Deck> decksById = new HashMap<>();

    public void save(Deck deck) {
        decksById.put(deck.getDeckId(), deck);
    }

    public Optional<Deck> findById(UUID id) {
        return Optional.ofNullable(decksById.get(id));
    }

    public Set<Deck> findDecksByName(Set<String> searchWords) {
        Set<Deck> returnSet = new HashSet<>();
        for (String word : searchWords) {
            String wordLowerCase = word.toLowerCase();
            for (Deck deck : decksById.values()) {
                if(deck.getName().toLowerCase().equals(wordLowerCase)){
                    returnSet.add(deck);
                }
            }
        }
        return returnSet;
    }

}