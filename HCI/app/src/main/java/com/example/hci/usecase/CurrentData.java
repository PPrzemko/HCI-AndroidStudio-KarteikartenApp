package com.example.hci.usecase;

import android.content.Context;

import com.example.hci.YourStacksActivity;

import java.util.UUID;

public class CurrentData {

    private UUID userId;
    private UUID deckId;

    private LearningSession learningSession;

    public YourStacksActivity getYourStacksActivity() {
        return yourStacksActivity;
    }

    public void setYourStacksActivity(YourStacksActivity yourStacksActivity) {
        this.yourStacksActivity = yourStacksActivity;
    }

    private YourStacksActivity yourStacksActivity;

    private static CurrentData instance;

    public static CurrentData getInstance() {
        if (instance == null) {
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

    public LearningSession getLearningSession() {
        return learningSession;
    }

    public void setLearningSession(LearningSession learningSession) {
        this.learningSession = learningSession;
    }
}
