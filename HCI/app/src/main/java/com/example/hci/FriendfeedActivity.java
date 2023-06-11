package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FriendfeedActivity extends AppCompatActivity {

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

    }
}