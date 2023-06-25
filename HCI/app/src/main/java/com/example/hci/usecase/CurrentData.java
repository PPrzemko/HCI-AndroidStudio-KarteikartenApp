package com.example.hci.usecase;

import com.example.hci.model.FlashCard;

import java.util.ArrayList;
import java.util.UUID;

public class CurrentData {

    private UUID userId;
    private UUID deckId;

    private LearningSession learningSession;

    public ArrayList<FlashCard> getCurrenedFilteredCards() {
        return currenedFilteredCards;
    }

    public void setCurrenedFilteredCards(ArrayList<FlashCard> currenedFilteredCards) {
        this.currenedFilteredCards = currenedFilteredCards;
    }

    private ArrayList<FlashCard> currenedFilteredCards;
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
