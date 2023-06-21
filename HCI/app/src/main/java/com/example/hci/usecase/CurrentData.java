package com.example.hci.usecase;

import java.util.UUID;

public class CurrentData {

    private UUID userId;
    private UUID deckId;

    private static CurrentData instance;

    public static CurrentData getInstance(){
        if(instance == null){
            instance = new CurrentData();
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
