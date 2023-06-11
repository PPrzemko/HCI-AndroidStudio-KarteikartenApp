package com.example.hci.usecase;

import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;

public class UserRegistration { //Instanz muss in main oder so erzeugt werden
    private String username, email, password;

    public UserRegistration(){
        //Singleton sinnvoll?
    };

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Felder müssen Variblen der klasse setzen, button ruft diese Funktion auf
    public void createUser(String username, String  email, String password){
        User user = new User(username, email, password);
        UserRepository userRepo=new UserRepository();
    }
}
