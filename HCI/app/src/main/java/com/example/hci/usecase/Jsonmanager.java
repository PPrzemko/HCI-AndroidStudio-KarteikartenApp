package com.example.hci.usecase;

import android.content.Context;

import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.UUID;


public class Jsonmanager {

    private static Jsonmanager instance;
    public static Jsonmanager getInstance(){
        if(instance == null){
            instance = new Jsonmanager();
        }
        return instance;
    }

    public void writeToJson(Context context) {

        UserRepository userRepository = UserRepository.getInstance();
        File file = new File(context.getExternalFilesDir(null), "data.txt");

        JSONArray jsonArray = new JSONArray();

        for(Map.Entry<UUID, User> entry : UserRepository.getInstance().getUsersList().entrySet())

        {
            JSONObject aUser = new JSONObject();
            try {
                aUser.put("Name", entry.getValue().getUsername());
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
            try {
                aUser.put("Email", entry.getValue().getEmail());
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
            try {
                aUser.put("Password", entry.getValue().getPassword());
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }

            jsonArray.put(aUser);
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(jsonArray.toString());
            writer.flush();
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //UserRepository.getInstance().getUsersList().clear();

    }
}








