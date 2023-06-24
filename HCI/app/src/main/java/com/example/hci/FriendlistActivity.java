package com.example.hci;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import androidx.appcompat.widget.SearchView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.CurrentData;
import com.example.hci.usecase.Jsonmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.UUID;


public class FriendlistActivity extends AppCompatActivity {

    private CurrentData currentData = CurrentData.getInstance();
    private UserRepository userRepository= UserRepository.getInstance();
    private Jsonmanager jsonmanager = Jsonmanager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);

        TextView textView = findViewById(R.id.textViewFriendlistHeadline);
        textView.setText("Freundesliste");

        LinearLayout linear =findViewById(R.id.LinearLayoutFriendlist);
        User neu = userRepository.findById(currentData.getUserId());

        TextView[] textViews = new TextView[20];
        String alleFreunde = "";
        for(User u : neu.getFriends()){
            alleFreunde += u.getUsername() ;
            alleFreunde += "\n";
        }
        for(TextView txt : textViews){
            txt = new TextView(this);
            txt.setText(alleFreunde);
            txt.setPadding(0,40,0,40);
            txt.setGravity(Gravity.CENTER);
            linear.addView(txt);
        }
        navbar();

        SearchView searchView = findViewById(R.id.SearchViewFriendlist);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                User newFriend = userRepository.findbyUserName2(query);
                User momentanerUser = userRepository.findById(currentData.getUserId());
                momentanerUser.addFriend(newFriend);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Aktion, die bei Änderungen des Suchtextes ausgeführt werden soll
                return true;
            }
        });
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