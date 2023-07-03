package com.example.hci;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter2 extends RecyclerView.ViewHolder {
    TextView name;
    public CustomAdapter2(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.freundeName);
    }


}
