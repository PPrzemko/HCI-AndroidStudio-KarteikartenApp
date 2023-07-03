package com.example.hci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hci.model.Deck;
import com.example.hci.model.User;

import java.util.ArrayList;
import java.util.UUID;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter2> {

    ArrayList<User>allFriends;
    Context context;

    public CustomAdapter(ArrayList<User>giveallFriends, Context context){
        this.allFriends = giveallFriends;
        this.context = context;
    }




    @NonNull
    @Override
    public CustomAdapter2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomAdapter2(LayoutInflater.from(context).inflate(R.layout.activity_friendlist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter2 holder, int position) {
        holder.name.setText(allFriends.get(position).getUsername());

    }



    @Override
    public int getItemCount() {
        return allFriends.size();
    }

    public void setItemList(ArrayList<User> newList){
        allFriends = newList;
        notifyDataSetChanged();
    }

}



