package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci.model.Achievement;
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
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.UUID;

public class FriendfeedActivity extends AppCompatActivity {

    private UserRepository userRepository= UserRepository.getInstance();
    private CurrentData currentData = CurrentData.getInstance();
    private Jsonmanager jsonmanager = Jsonmanager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendfeed);
        User momentaneruser = userRepository.findById(currentData.getUserId());

        TextView textView = findViewById(R.id.textViewHeadline);
        textView.setText("Aktivitäten");

        LinearLayout linearLayout = findViewById(R.id.linearLayoutFriendfeed);
        for(User user : momentaneruser.getFriends()){
           TextView aktivi = new TextView(this);
           String scoreString = String.valueOf(user.getScore());
           int letztesElement = user.getAchievements().size()-1;
           Achievement letztesAchievement = user.getLatestAchievements();
           if(letztesAchievement != null){
               String ausgabe = user.getUsername() + " hat folgendes Deck gelernt:  " + letztesAchievement.getDeckname() + " - Score " + scoreString + System.lineSeparator();
               aktivi.setText(ausgabe);
               linearLayout.addView(aktivi);
           }else{
               Log.d("Error:", "FriendFeedActivity: " + "Keine Achievements vorhanden");
           }

        }

        navbar();
    }
    public void navbar(){
        ImageButton a=findViewById(R.id.btnActivity);
        ImageButton b=findViewById(R.id.btnFriendslist);
        ImageButton c=findViewById(R.id.btnStack);
        ImageButton d=findViewById(R.id.btnProfile);

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