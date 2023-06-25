package com.example.hci.usecase;

import android.content.Context;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
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
import java.util.ArrayList;
import java.util.HashMap;
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
            JSONArray ganzerStapel = new JSONArray();
            for (Map.Entry<UUID, Deck> entry2 : entry.getValue().getOwnDecks().entrySet()) {
                String deckName = entry2.getValue().getName();
                ArrayList<FlashCard> flashCards = entry2.getValue().getFlashCards();
                JSONArray einDeck = new JSONArray();
                for (FlashCard cardEntry : flashCards) {

                    JSONObject karte = new JSONObject();
                    try {
                        karte.put("Deckname", deckName);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        karte.put("Voderseite", cardEntry.getFront());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        karte.put("Rückseite", cardEntry.getBack());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    ganzerStapel.put(karte);
                }
                }
                try {
                    aUser.put("Stapel", ganzerStapel);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                JSONArray alleFreunde = new JSONArray();
                for (User friends : entry.getValue().getFriends()) {
                    JSONObject einFreund = new JSONObject();
                    try {
                        einFreund.put("Username", friends.getUsername());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    alleFreunde.put(einFreund);
                }
            try {
                aUser.put("Alle Freunde", alleFreunde);
            } catch (JSONException e) {
                throw new RuntimeException(e);
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


                String email = person.get("Email").toString();


                String password = person.get("Password").toString();

                User user = new User(name, email, password);
                UserRepository.getInstance().save(user);


                /*JSONArray innerArray;
                innerArray = (JSONArray) person.get("Stapel");*/

                String jsonStringStapel = person.get("Stapel").toString();
                JSONArray jsonArray = new JSONArray(jsonStringStapel);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    String deckName = (String) obj.get("Deckname");
                    String voderseite = (String) obj.get("Voderseite");
                    String rueckseite = (String) obj.get("Rückseite");

                    FlashCard flashCard = new FlashCard(voderseite, rueckseite);

                    HashMap<UUID, Deck> enstandesDeck = user.getOwnDecks();
                    boolean existiert = false;
                    for (Map.Entry<UUID, Deck> entry2 : enstandesDeck.entrySet()) {
                        if(entry2.getValue().getName().equals(deckName)){
                            Deck deck = entry2.getValue();
                            deck.addFlashCard(flashCard);
                            existiert = true;
                            break;
                        }
                        existiert = false;
                    }
                    if(existiert == false){
                        Deck neuesDeck = new Deck(deckName);
                        enstandesDeck.put(neuesDeck.getDeckId(), neuesDeck);
                        neuesDeck.addFlashCard(flashCard);
                    };

                }




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
        } catch (IOException | ParseException | JSONException e) {
            e.printStackTrace();
        }
    }
}








