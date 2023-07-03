package com.example.hci.repositories;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.hci.YourStacksActivity;
import com.example.hci.model.User;


import java.util.*;
import java.util.stream.Collectors;

public class UserRepository  { //TODO Singleton werden


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


    public User checkLoginCreds(String givenusername, String givenpassword){
        // Checks User credentials and returns User if found, else null
        User returnUser = null;
        if(UserRepository.getInstance().getUsersList().size() == 0){
            returnUser = null;
        }else {
            try{
                for (User entry : getUsersList().values()) {
                    if (entry.getUsername().equals(givenusername) && entry.getPassword().equals(givenpassword)) {
                        returnUser = entry;
                        break;
                    }
                }

            }catch (Exception e){
                Log.d("TEST", "Gibt den User schon" + e.toString());
                returnUser = null;
            }

        }
        return returnUser;
    }


    public boolean createNewUser(User user){
        // creates a new User and returns true if successful, else false it already exists
        boolean wurdeErfolgreichErstellt = false;
        if(usersList.isEmpty()){
            usersList.put(user.getUserId(), user);
            wurdeErfolgreichErstellt = true;
        }else{
            try{
                boolean userFound = false;
                for (User entry : getUsersList().values()) {
                    if (entry.getUsername().equals(user.getUsername())) {
                        Log.d("TEST", "Gibt den User schon");
                        userFound = true;
                    }
                }
                if(!userFound){
                    usersList.put(user.getUserId(), user);
                    wurdeErfolgreichErstellt = true;
                }

            }catch (Exception e){
                Log.d("TEST", "Gibt den User schon" + e.toString());
                wurdeErfolgreichErstellt = false;
            }

        }

        return wurdeErfolgreichErstellt;
    }



}
