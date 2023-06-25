package com.example.hci;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.usecase.CurrentData;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public class YourStacksCustomViewAdapter extends RecyclerView.Adapter<YourStacksCustomViewHolder> {

    List<Deck> DeckList;
    Context context;
    CurrentData currentData=CurrentData.getInstance();

    public YourStacksCustomViewAdapter(List<Deck> givendeckList, Context context){
        this.DeckList = givendeckList;
        this.context = context;
    }

    @NonNull
    @Override
    public YourStacksCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YourStacksCustomViewHolder(LayoutInflater.from(context).inflate(R.layout.deck_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull YourStacksCustomViewHolder holder, int position) {
        holder.name.setText( DeckList.get(position).getName());
        holder.count.setText( DeckList.get(position).getFlashCards().size() + " Karten");
        //holder.setDeckId(DeckList.get(position).getDeckId());
        UUID currentDeckId = DeckList.get(position).getDeckId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentData.setDeckId(currentDeckId);
                currentData.getYourStacksActivity().fakeOnClickListener();
            }
        });
    }

    @Override
    public int getItemCount() {
        return DeckList.size();
    }

    public void setItemList(ArrayList<Deck> newList){
        DeckList = newList;
        notifyDataSetChanged();
    }

}
