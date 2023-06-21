package com.example.hci.usecase;

import android.content.Context;

import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public void readFromJson(Context context){
        try {

            //JSON DATEI LESEN
            String fileName = "data.txt";
            String path = context.getApplicationContext().getExternalFilesDir(null).getPath();
            String filePath = path + "/" + fileName;
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            //********IN HASH-MAP BRINGEN *****

            JSONParser parser = new JSONParser();
            org.json.simple.JSONArray a = (org.json.simple.JSONArray) parser.parse(new FileReader(filePath));

            for (Object o : a) {
                org.json.simple.JSONObject person = (org.json.simple.JSONObject) o;

                String name = (String) person.get("Name");


                String email = (String) person.get("Email");


                String password = (String) person.get("Password");

                User user = new User(name, email, password);
                UserRepository.getInstance().save(user);

            }

                    /*String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append("\n");
                    }*/

            reader.close();
            fis.close();


            //String fileContents = stringBuilder.toString();

            //Log.d("HIER IST DER STRING",fileContents);
            // Use the fileContents variable which contains the contents of the file
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}








