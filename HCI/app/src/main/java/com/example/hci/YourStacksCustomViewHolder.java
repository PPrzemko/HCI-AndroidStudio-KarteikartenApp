package com.example.hci;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;

public class YourStacksCustomViewHolder extends RecyclerView.ViewHolder {

    TextView name, count;
    Deck deck;

    public YourStacksCustomViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.DeckName);
        count = itemView.findViewById(R.id.DeckCount);





        /*name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //fruit.doSomething();
                //Toast.makeText(view.getContext(), name.getText() + " clicked!", Toast.LENGTH_SHORT).show();
            }
        });*/




    }
}
