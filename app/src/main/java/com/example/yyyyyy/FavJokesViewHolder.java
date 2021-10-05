package com.example.yyyyyy;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavJokesViewHolder extends RecyclerView.ViewHolder {
    private TextView content;
    private Button item_number;


    public FavJokesViewHolder(@NonNull View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.content);
        item_number = itemView.findViewById(R.id.item_number);
    }

    public TextView getContent() {
        return content;
    }

    public Button getItem_number() {
        return item_number;
    }
}
