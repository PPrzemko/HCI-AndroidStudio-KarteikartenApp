package com.example.hci.repositories;

import com.example.hci.model.User;


import java.util.*;

public class UserRepository { //TODO Singleton werden


    public HashMap<UUID, User> getUsersList() {
        return usersList;
    }

    private final HashMap<UUID, User> usersList = new HashMap<>();

    private static UserRepository instance;
    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public void save(User benutzer) {
        usersList.put(benutzer.getUserId(), benutzer);
    }

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(usersList.get(id));
    }

    public Set<User> findDecksByName(Set<String> searchWords) {
        Set<User> returnSet = new HashSet<>();
        for (String word : searchWords) {
            String wordLowerCase = word.toLowerCase();
            for (User user : usersList.values()) {
                if(user.getUsername().toLowerCase().equals(wordLowerCase)){
                    returnSet.add(user);
                }
            }
        }
        return returnSet;
    }
}
