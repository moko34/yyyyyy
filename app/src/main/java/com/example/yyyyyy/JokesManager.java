package com.example.yyyyyy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Map;

public class JokesManager {
    private final Context context;

    public JokesManager(Context context) {
        this.context = context;
    }

    public void saveJokes(Joke joke){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(joke.getContent(),joke.getLiked());
        editor.apply();
    }

    public ArrayList<Joke> retrieveJokes(){
        ArrayList<Joke> jokeArrayList = new ArrayList<>();
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
        Map<String,?> jokes = sharedPreferences.getAll();
        for(Map.Entry<String,?> entry :jokes.entrySet()){
            Joke joke=new Joke(entry.getKey(), (Boolean) entry.getValue());
            jokeArrayList.add(joke);
        }
        return jokeArrayList;
    }

    public void deleteJoke (String jokeContent){
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(jokeContent);
        editor.commit();
    }
}
