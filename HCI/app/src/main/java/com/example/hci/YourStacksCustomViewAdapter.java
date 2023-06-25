package com.example.hci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;

import java.util.ArrayList;
import java.util.List;

public class YourStacksCustomViewAdapter extends RecyclerView.Adapter<YourStacksCustomViewHolder> {

    List<Deck> DeckList;
    Context context;

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
        //holder.count.setText(  DeckList.get(position).getDeckId().toString());
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
