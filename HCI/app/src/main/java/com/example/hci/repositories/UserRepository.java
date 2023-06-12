package com.example.hci.repositories;

import com.example.hci.model.Deck;
import com.example.hci.model.User;


import java.util.*;

public class UserRepository { //TODO Singleton werden
    public HashMap<UUID, User> getUsersById() {
        return usersById;
    }

    private final HashMap<UUID, User> usersById = new HashMap<>();

    public UserRepository(){
        //Singleton!!
    }
    public void save(User deck) {
        usersById.put(deck.getUserId(), deck);
    }

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(usersById.get(id));
    }

    public Set<User> findDecksByName(Set<String> searchWords) {
        Set<User> returnSet = new HashSet<>();
        for (String word : searchWords) {
            String wordLowerCase = word.toLowerCase();
            for (User user : usersById.values()) {
                if(user.getUsername().toLowerCase().equals(wordLowerCase)){
                    returnSet.add(user);
                }
            }
        }
        return returnSet;
    }
}
