package com.example.yyyyyy;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JokesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JokesFragment extends Fragment  {
    private JokesAdapter jokesAdapter;
    private RecyclerView recyclerView;
    private static ArrayList<Joke> fragmentJokes;
    private JokesAdapter.JokeIsLiked jokeIsLiked;
    public JokesFragment() {
        // Required empty public constructor
    }



    public static JokesFragment newInstance(ArrayList<Joke> jokes) {
        JokesFragment fragment = new JokesFragment();
        fragmentJokes=jokes;
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof JokesAdapter.JokeIsLiked){
            jokeIsLiked= (JokesAdapter.JokeIsLiked) context;
           jokesAdapter=new JokesAdapter(jokeIsLiked,fragmentJokes,context);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView=view.findViewById(R.id.jokeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(jokesAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jokes_fragment, container, false);
        // Inflate the layout for this fragment
        return  view;

    }

}