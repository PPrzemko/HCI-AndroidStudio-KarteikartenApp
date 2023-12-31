package com.example.hci;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.CurrentData;
import com.example.hci.usecase.Jsonmanager;


public class FriendlistActivity extends AppCompatActivity {

    private CurrentData currentData = CurrentData.getInstance();
    private UserRepository userRepository= UserRepository.getInstance();
    private Jsonmanager jsonmanager = Jsonmanager.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        navbar();
        long delayMillis = 2000;

        //listView = (ListView) findViewById(R.id.listview1);


        User neu = userRepository.findById(currentData.getUserId());

        LinearLayout linearLayout = findViewById(R.id.linearlayout);

        for(User friends : neu.getFriends()){
            TextView einFreund = new TextView(this);
            einFreund.setTextSize(20);
            einFreund.setText(friends.getUsername());
            linearLayout.addView(einFreund);
        }

        TextView MeldungHinzugefuegt = findViewById(R.id.Meldung2);
        MeldungHinzugefuegt.setTextSize(15);
        MeldungHinzugefuegt.setVisibility(View.INVISIBLE);



        Button freundesuchenButton = findViewById(R.id.buttonFreunde);

        freundesuchenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView usernameEingabe = findViewById(R.id.UsernameEingeben);
                String userNameEingabeString = usernameEingabe.getText().toString();
                if(userRepository.checkIfUserExists(userNameEingabeString)){
                    User neuerFreund = userRepository.findUserByName(userNameEingabeString);
                    if(neu.addFriend(neuerFreund)){
                        MeldungHinzugefuegt.setText("User wurde zu deinen Freuden hinzugefügt");
                        MeldungHinzugefuegt.setVisibility(View.VISIBLE);

                        LinearLayout linearLayout = findViewById(R.id.linearlayout);
                        linearLayout.removeAllViews();
                        for(User friends : neu.getFriends()){
                            TextView einFreund2 = new TextView(FriendlistActivity.this);
                            einFreund2.setText(friends.getUsername());
                            einFreund2.setTextSize(20);
                            linearLayout.addView(einFreund2);
                        }
                    }
                    else{
                        MeldungHinzugefuegt.setText("User ist bereits in deiner Freundesliste");
                        MeldungHinzugefuegt.setVisibility(View.VISIBLE);
                    }

                }
                else{
                     MeldungHinzugefuegt.setText("User existiert nicht");

                     MeldungHinzugefuegt.setVisibility(View.VISIBLE);

                }
                MeldungHinzugefuegt.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MeldungHinzugefuegt.setVisibility(View.INVISIBLE);
                        usernameEingabe.setText("");
                    }
                },delayMillis);

            }
        });


        //TESTOBJEKT
        /*User momentanerUser = userRepository.findById(currentData.getUserId());
        User neuerFreund = new User("Luis", "gmx", "123");
        User neuerFreund2 = new User("Franzi", "gmx", "123");
        User neuerFreund3 = new User("Leon", "gmx", "123");
        User neuerFreund4 = new User("Milel", "gmx", "123");
        momentanerUser.addFriend(neuerFreund);
        momentanerUser.addFriend(neuerFreund2);
        momentanerUser.addFriend(neuerFreund3);
        momentanerUser.addFriend(neuerFreund4);
        //**********

        TextView[] textViews = new TextView[20];
        String alleFreunde = "";
        for (User u : neu.getFriends()) {
            alleFreunde += u.getUsername();
            alleFreunde += "\n";
        }
        for (TextView txt : textViews) {
            txt = new TextView(this);
            txt.setText(alleFreunde);
            txt.setPadding(0, 40, 0, 40);
            txt.setGravity(Gravity.CENTER);
            linear.addView(txt);
        }*/

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