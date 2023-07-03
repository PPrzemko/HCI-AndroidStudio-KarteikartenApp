package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hci.model.User;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.CurrentData;


public class ForgotPassword extends AppCompatActivity {

    private UserRepository userRepository = UserRepository.getInstance();
    private CurrentData currentData = CurrentData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button neu = findViewById(R.id.passwortNeu);
        Button abbruch = findViewById(R.id.abrechenButton);

        Bundle b = getIntent().getExtras();
        Boolean cameFromProfile = b.getBoolean("cameFromProfile");

        TextView tvUsername = findViewById(R.id.TextEditUserChangePw);
        TextView pwInput1TextEdit = findViewById(R.id.TextEditInput1changePw);
        TextView pwInput2TextEdit = findViewById(R.id.TextEditInput2changePw);
        TextView DisplayErrorMessageTextView = findViewById(R.id.DisplayErrorMessageChangePw);
        TextView benutzernameAnzeige = findViewById(R.id.benutzernameChangePw);

        User currentUser = userRepository.findById(currentData.getUserId());

        if (cameFromProfile) {
            tvUsername.setText(currentUser.getUsername());
            tvUsername.setVisibility(View.INVISIBLE);
            benutzernameAnzeige.setVisibility(View.INVISIBLE);
        }

        neu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                if (cameFromProfile) {
                    i = new Intent(getApplicationContext(), profile.class);
                } else {
                    i = new Intent(getApplicationContext(), MainActivity.class);
                }

                if (pwInput1TextEdit.getText().toString().equals(pwInput2TextEdit.getText().toString())) {
                    currentUser.setPassword(pwInput1TextEdit.getText().toString());
                    DisplayErrorMessageTextView.setVisibility(View.INVISIBLE);
                    startActivity(i);
                } else {
                    DisplayErrorMessageTextView.setVisibility(View.VISIBLE);
                }
            }
        });
        
        abbruch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                if (cameFromProfile) {
                    i = new Intent(getApplicationContext(), profile.class);
                } else {
                    i = new Intent(getApplicationContext(), MainActivity.class);
                }
                startActivity(i);
            }
        });
    }
}