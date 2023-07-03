package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hci.model.Deck;
import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.CurrentData;
import com.example.hci.usecase.Jsonmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

//currentDeck.getFlashCards() als currenedFilteredCards setzen
public class YourStacksActivity extends AppCompatActivity {

    private Jsonmanager jsonmanager = Jsonmanager.getInstance();
    RecyclerView recyclerView;

    YourStacksCustomViewAdapter customViewAdapter;

    CurrentData currentData = CurrentData.getInstance();
    UserRepository userRepository = UserRepository.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_stacks);

        TextView textView = findViewById(R.id.textViewYourStacksHeadline);
        textView.setText("Deine Stapel");

        UUID id=currentData.getUserId();
        Log.d("HHHHHHHHHHHHHHHHHHH", id.toString());
        User currentUser = userRepository.findById(id);
        Log.d("DDDDDDDDDDD", currentUser.toString());
        if(currentUser ==null)
            return;
        HashMap<UUID,Deck> allDecksMap = currentUser.getOwnDecks(); ////////////////////////////////////////////////////////////

         ArrayList<Deck> allDecksList = new ArrayList<Deck>();
         for(Deck deck : allDecksMap.values()){
             allDecksList.add(deck);
         }

        currentData.setYourStacksActivity(this);

        recyclerView = findViewById(R.id.recyclerViewStapel);
        customViewAdapter = new YourStacksCustomViewAdapter(allDecksList, getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customViewAdapter);

        Button das = findViewById(R.id.buttonAddCard);
        das.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), editCard.class);
                startActivity(i);
            }
        });

        Button stapelErstellen = findViewById(R.id.buttonCreateStack);

        stapelErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), create_new_deckActivity.class);
                startActivity(i);
            }
        });

        navbar();
    }

    public void fakeOnClickListener(){
        Intent i = new Intent(getApplicationContext(), stackpreview.class);
        startActivity(i);
    }

    public void navbar() {
        ImageButton a = findViewById(R.id.btnActivity);
        ImageButton b = findViewById(R.id.btnFriendslist);
        ImageButton c = findViewById(R.id.btnStack);
        ImageButton d = findViewById(R.id.btnProfile);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FriendfeedActivity.class);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FriendlistActivity.class);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);
                startActivity(i);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile.class);
                startActivity(i);
            }
        });

    }
}