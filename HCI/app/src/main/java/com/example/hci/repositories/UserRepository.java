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
        if(usersList.containsKey(benutzer.getUserId())){
            throw new IllegalArgumentException("User already exists");
        }else{
            usersList.put(benutzer.getUserId(), benutzer);
        }
    }

    public User findById(UUID id) {
        return usersList.get(id);
    }

    public Set<User> findUserByName(Set<String> searchWords) {
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

    public User findbyUserName2(String name){
        User gefundenerUser = null;
        for(User user : usersList.values()){
            if(user.getUsername().equals(name)){
                gefundenerUser = user;
                break;
            }
        }
        return gefundenerUser;
    };

    public boolean findbyUserName3(String name){
        boolean existiert = false;
        for(User user : usersList.values()){
            if(user.getUsername().equals(name)){
                existiert = true;
                break;
            }
                existiert = false;
        }
        return existiert;

    };

}
