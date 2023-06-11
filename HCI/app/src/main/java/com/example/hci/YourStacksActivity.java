package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YourStacksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_stacks);

        TextView textView = findViewById(R.id.textViewYourStacksHeadline);
        textView.setText("Deine Stapel");

        LinearLayout linear =findViewById(R.id.linearLayoutYourStacks);
        TextView[] textViews = new TextView[20];

        for(TextView txt : textViews){
            txt = new TextView(this);
            txt.setText("Englisch 68");
            txt.setPadding(0,40,0,40);
            txt.setGravity(Gravity.CENTER);
            linear.addView(txt);
        }
    }
}