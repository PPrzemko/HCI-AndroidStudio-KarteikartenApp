package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class editCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);


        // https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Chemie", "Mathe", "Deutsch"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);











    }
}