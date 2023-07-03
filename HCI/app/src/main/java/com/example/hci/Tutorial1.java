package com.example.hci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Tutorial1 extends AppCompatActivity {

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial1);

        ImageView img = (ImageView) findViewById(R.id.image232);
        img.setImageResource(R.drawable.tut1);



        Button nextbtn = findViewById(R.id.NextBtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count==0){
                    img.setImageResource(R.drawable.tut2);
                } else if (count==1) {
                    img.setImageResource(R.drawable.tut3);
                }else if (count==2) {
                    img.setImageResource(R.drawable.tut4);
                } else if (count==3) {
                    Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);
                    startActivity(i);
                }

                count=count+1;



            }
        });

        Button skipbtn = findViewById(R.id.skipbtn);
        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), YourStacksActivity.class);
                startActivity(i);
            }
        });



    }
}