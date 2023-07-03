package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        TextView textView = findViewById(R.id.textViewHeadline);
        textView.setText("Aktivitäten");

        LinearLayout linear =findViewById(R.id.linearLayoutFriendfeed);
        TextView[] textViews = new TextView[20];

        for(TextView txt : textViews){
            txt = new TextView(this);
            txt.setText("XYZ hat den Stapel ZYX erstellt");
            txt.setPadding(0,40,0,40);
            txt.setGravity(Gravity.CENTER);
            linear.addView(txt);
        }

        LinearLayout linear2 = findViewById((R.id.anzahlUsers));
        int size = UserRepository.getInstance().getUsersList().size();
        TextView textView1;

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