package com.example.hci.usecase;

import com.example.hci.repositories.DeckRepository;

import java.util.UUID;

public class CurrentData {

    private UUID userId;
    private UUID deckId;

    private static RememberCurrentData instance;

    public static RememberCurrentData getInstance(){
        if(instance == null){
            instance = new RememberCurrentData();
        }
        return instance;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getDeckId() {
        return deckId;
    }

    public void setDeckId(UUID deckId) {
        this.deckId = deckId;
    }
}
