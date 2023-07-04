package com.example.hci;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hci.model.FlashCard;

public class StackPreviewCustomViewHolder extends RecyclerView.ViewHolder {

    TextView front, back;
    FlashCard card;

    public StackPreviewCustomViewHolder(@NonNull View itemView) {
        super(itemView);
        front = itemView.findViewById(R.id.cardFrontTxt);
        back = itemView.findViewById(R.id.cardBackTxt);




    }
}
