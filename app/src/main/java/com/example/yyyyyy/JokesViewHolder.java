package com.example.yyyyyy;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class JokesViewHolder extends RecyclerView.ViewHolder {
    public TextView jokeText,isLikedText;
    private final ImageButton btnLike,btnShare;
    private CardView cardView;
    public JokesViewHolder(@NonNull View itemView) {
        super(itemView);
        jokeText=itemView.findViewById(R.id.jokeText);
        isLikedText=itemView.findViewById(R.id.likeText);
        btnLike=itemView.findViewById(R.id.likeButton);
        btnShare=itemView.findViewById(R.id.share);
        cardView=itemView.findViewById(R.id.card);
    }

    public TextView getJokeText() {
        return jokeText;
    }

    public TextView getIsLikedText() {
        return isLikedText;
    }

    public ImageButton getBtnLike() {
        return btnLike;
    }

    public ImageButton getBtnShare() {
        return btnShare;
    }

    public CardView
    getCardView() {
        return cardView;
    }
}
