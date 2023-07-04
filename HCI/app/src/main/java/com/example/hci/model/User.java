package com.example.hci.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User{

    private UUID userId;
    private String username, email, password;
    private ArrayList<User> friends;
    private ArrayList<Achievement> achievements;
    private HashMap<UUID, Deck> ownDecks;

    int score;

    public User(String username, String email, String password){
        this.username=username;
        this.email=email;
        this.password=password;
        this.score = 0;
        userId=UUID.randomUUID();
        friends=new ArrayList<>();
        achievements=new ArrayList<>();
        ownDecks=new HashMap<>();
    }
    public User(String username, String email, String password, int score){
        this.username=username;
        this.email=email;
        this.password=password;
        this.score = score;
        userId=UUID.randomUUID();
        friends=new ArrayList<>();
        achievements=new ArrayList<>();
        ownDecks=new HashMap<>();
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    public HashMap<UUID, Deck> getOwnDecks() {
        // DONT every use DEPRECATED
        return ownDecks;
    }

    public ArrayList<Deck> getOwnDecksAsArray(){
        ArrayList<Deck> allDecksList = new ArrayList<Deck>();
        for(Deck deck : ownDecks.values()){
            allDecksList.add(deck);
        }
        return allDecksList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setScore(int zahl) {
        this.score = score + zahl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addAchievment(Achievement achievement){
        achievements.add(achievement);
    }

    public void addFriend(User friend){
        friends.add(friend);
    }

    public void addOwnDeck(Deck deck){
        ownDecks.put(deck.getDeckId(), deck);
    }
}
