package com.example.hci;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.usecase.CurrentData;

import java.util.UUID;

public class YourStacksCustomViewHolder extends RecyclerView.ViewHolder {

    TextView name, count;
    Deck deck;
    CurrentData currentData = CurrentData.getInstance();

    public void setDeckId(UUID deckId) {
        this.deckId = deckId;
    }

    UUID deckId;

    public YourStacksCustomViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.DeckName);
        count = itemView.findViewById(R.id.DeckCount);
    }
}
