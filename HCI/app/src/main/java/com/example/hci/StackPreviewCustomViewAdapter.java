package com.example.hci;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hci.model.FlashCard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StackPreviewCustomViewAdapter extends RecyclerView.Adapter<StackPreviewCustomViewHolder> {

    List<FlashCard> cardList;
    Context context;

    public StackPreviewCustomViewAdapter(List<FlashCard> givencardList, Context context){
        this.cardList = givencardList;
        this.context = context;
    }

    @NonNull
    @Override
    public StackPreviewCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StackPreviewCustomViewHolder(LayoutInflater.from(context).inflate(R.layout.carditem_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StackPreviewCustomViewHolder holder, int position) {
        try {
            holder.front.setText( cardList.get(position).getFront());
            holder.back.setText( cardList.get(position).getBack());
        } catch (Exception e) {
            Log.d("Error:", "onBindViewHolder: Cant set text in StackPreviewCustomViewAdapter");
        }


    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void setItemList(ArrayList<FlashCard> newList){
        cardList = newList;
        notifyDataSetChanged();
    }
}
