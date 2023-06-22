package com.example.hci.repositories;


import com.example.hci.model.Deck;
import com.example.hci.model.User;
import com.example.hci.usecase.CurrentData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Optional;

public class DeckRepository {

    UserRepository userRepository = UserRepository.getInstance();
    private CurrentData currentData = CurrentData.getInstance();
    private final HashMap<UUID, Deck> decksById = new HashMap<>();
    private static DeckRepository instance;
    public static DeckRepository getInstance(){
        if(instance == null){
            instance = new DeckRepository();
        }
        return instance;
    }

    public void save(Deck deck) {
        decksById.put(deck.getDeckId(), deck);
    }

    public Deck findById(UUID id) {
        return decksById.get(id);
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

    public Deck findDeckByName2(String deckname){
        User neuerUser = userRepository.findById(currentData.getUserId());

        for(Map.Entry<UUID, Deck>entry : neuerUser.getOwnDecks().entrySet()){
            String testName = entry.getValue().getName();
            if(entry.getValue().getName().equals(deckname)){
                 Deck deck = neuerUser.getOwnDecks().get(entry.getKey());

                return deck;
            }
        }

        return null;
    };

}