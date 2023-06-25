package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.Jsonmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class YourStacksActivity extends AppCompatActivity {

    private Jsonmanager jsonmanager = Jsonmanager.getInstance();
    RecyclerView recyclerView;

    YourStacksCustomViewAdapter customViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_stacks);

        TextView textView = findViewById(R.id.textViewYourStacksHeadline);
        textView.setText("Deine Stapel");

        List<FlashCard> cardList;
        cardList = new ArrayList<FlashCard>();
        cardList.add(new FlashCard("1+1", "2"));
        cardList.add(new FlashCard("10/2", "5"));
        cardList.add(new FlashCard("3*3", "9" ));
        cardList.add(new FlashCard("1+1", "2"));
        cardList.add(new FlashCard("10/2", "5"));
        cardList.add(new FlashCard("3*3", "9" ));
        cardList.add(new FlashCard("1+1", "2"));
        cardList.add(new FlashCard("10/2", "5"));
        cardList.add(new FlashCard("3*3", "9" ));

        List<Deck> decklist;
        recyclerView = findViewById(R.id.recyclerView);
        decklist = new ArrayList<Deck>();
        decklist.add(new Deck("Mathe"));
        decklist.add(new Deck("Deutsch"));
        decklist.add(new Deck("Englisch"));

        recyclerView = findViewById(R.id.recyclerView);
        customViewAdapter = new YourStacksCustomViewAdapter(decklist, getApplicationContext());

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // recyclerView.setAdapter(customViewAdapter);












        Button das = findViewById(R.id.buttonAddCard);
        das.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), editCard.class);
                startActivity(i);
            }
        });

        Button untenlinks = findViewById(R.id.buttonCreateStack); //beide buttens sind der gleiche!!

        untenlinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    public void navbar() {
        ImageButton a = findViewById(R.id.btnActivity);
        ImageButton b = findViewById(R.id.btnFriendslist);
        ImageButton c = findViewById(R.id.btnStack);
        ImageButton d = findViewById(R.id.btnProfile);
        ImageButton e = findViewById(R.id.btnLogout);
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
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO ADD LOGOUT FUNCTIONALITY
                //ERSTELLEN EINER JSON
                jsonmanager.writeToJson(getApplicationContext());
                UserRepository.getInstance().getUsersList().clear();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}