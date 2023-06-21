package com.example.hci.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User {

    private UUID userId;
    private String username, email, password;
    private ArrayList<User> friends;
    private ArrayList<Achievement> achievements;
    private HashMap<UUID, Deck> ownDecks, adoptedDecks;

    public User(String username, String email, String password){
        this.username=username;
        this.email=email;
        this.password=password;
        userId=UUID.randomUUID();
        friends=new ArrayList<>();
        achievements=new ArrayList<>();
        ownDecks=new HashMap<>();
        adoptedDecks=new HashMap<>();
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

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    public HashMap<UUID, Deck> getOwnDecks() {
        return ownDecks;
    }

    public HashMap<UUID, Deck> getAdoptedDecks() {
        return adoptedDecks;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void addAdoptedDeck(Deck deck){
        adoptedDecks.put(deck.getDeckId(), deck);
    }

    public void addOwnDeck(Deck deck){
        ownDecks.put(deck.getDeckId(), deck);
    }


    public Boolean areBothPasswordsTheSame(String input1, String input2) { //incorrect password exception klasse kann gelöscht werden
        if(!input1.equals(input2)){
            return false;
        }
        return true;
    }
}
