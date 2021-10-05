package com.example.yyyyyy;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.security.SecureRandom;
import java.util.ArrayList;

public class JokesAdapter extends RecyclerView.Adapter<JokesViewHolder> {

    interface JokeIsLiked{
        void hasLikedJoke(Joke joke);
        void hasSharedJoke(Joke joke);
    }

    private JokeIsLiked jokeIsLiked;
    private ArrayList<Joke> yoMamaJokes;
    private Context context;
    private int a,r,g,b;
    int lastPosition=-1;

    public JokesAdapter(JokeIsLiked jokeIsLiked, ArrayList<Joke> yoMamaJokes, Context context) {
        this.jokeIsLiked = jokeIsLiked;
        this.yoMamaJokes = yoMamaJokes;
        this.context = context;
    }

    @NonNull
    @Override
    public JokesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_view,parent,false);
        JokesViewHolder jokesViewHolder = new JokesViewHolder(view);
        return jokesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JokesViewHolder holder, int position) {
        SecureRandom random=new SecureRandom();
        a = 1 + random.nextInt(255);
        r = 1 + random.nextInt(255);
        g = 1 + random.nextInt(255);
        b = 1 + random.nextInt(255);
        holder.cardView.setCardBackgroundColor(Color.argb(a,r,g,b));
        Joke joke=new Joke(yoMamaJokes.get(position).getContent(),false);
       holder.getJokeText().setText(joke.getContent());
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_translate_animation));
       holder.getJokeText().setText(joke.getContent());
        holder.getBtnLike().setOnClickListener(v -> {
                joke.setLiked(true);
                jokeIsLiked.hasLikedJoke(joke);
                Snackbar.make(holder.getBtnLike(),context.getString(R.string.liked,position+1), BaseTransientBottomBar.LENGTH_LONG).show();
        }
        );
        holder.getBtnShare().setOnClickListener(view ->{
            jokeIsLiked.hasSharedJoke(joke);
        });

    }

    @Override
    public int getItemCount() {
        return yoMamaJokes.size();
    }

    public  void setAnimation(View viewToAnimate,int position){
        if (position>lastPosition){
            Animation animation=AnimationUtils.loadAnimation(context,R.anim.fade_translate_animation);
            viewToAnimate.startAnimation(animation);
            lastPosition=position;
        }

    }
}
