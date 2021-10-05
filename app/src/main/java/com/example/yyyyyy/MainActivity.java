package com.example.yyyyyy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentContainerView;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements ParserTask.JSONDataLoaded,JokesAdapter.JokeIsLiked, View.OnClickListener,ShakeDetector.OnShakeListener{
    private FragmentContainerView fragmentContainerView;
    private JokesFragment jokesFragment;
    private SensorManager sensorManager;
    private Sensor sensor;
    private ShakeDetector shakeDetector;
    private ArrayList<Joke> favJokesArray,staticJokes = new ArrayList<>();
    private JokesManager jokesManager;
    private TextView jokes,favJokes;
    private boolean isJokesActive;
    private LinearLayout  linearLayout;
    private Drawable color,fav_jokes_drawable;
    private FavJokesFragment favJokesFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokes=findViewById(R.id.joke);
        favJokes=findViewById(R.id.fav_jokes);
        linearLayout=findViewById(R.id.linearLayout);
        fav_jokes_drawable=getDrawable(R.drawable.fav_fragment);
        fragmentContainerView=findViewById(R.id.fragmentContainer);
        new FetchTask(this).execute();
        jokesManager=new JokesManager(this);
        favJokesArray = jokesManager.retrieveJokes();
        jokes.setOnClickListener(this);
        favJokes.setOnClickListener(this);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector=new ShakeDetector();
        shakeDetector.setOnShakeListener(this);


    }

    public Bitmap convertImageToBitmap(Context context, int drawable){
        Drawable drawable1= ContextCompat.getDrawable(context,drawable);
        Bitmap bitmap = Bitmap.createBitmap(drawable1.getIntrinsicWidth(),drawable1.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        drawable1.setBounds(0,canvas.getHeight(),canvas.getWidth(),0);
        drawable1.draw(canvas);
        return bitmap;
    }

    @Override
    public void JSONDateReady(ArrayList<Joke> jokes) {
        staticJokes=jokes;
      jokesFragment=JokesFragment.newInstance(jokes);
      isJokesActive=true;
     getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,jokesFragment).commit();
    }

    @Override
    public void hasLikedJoke(Joke joke) {
        jokesManager.saveJokes(joke);
        favJokesArray.add(joke);

    }

    @Override
    public void hasSharedJoke(Joke joke) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String shareBody=joke.getContent();
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Joke");
        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(intent,getString(R.string.share)));
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.joke:
                if(isJokesActive){
                    return;
                }else{
                    jokesFragment=JokesFragment.newInstance(staticJokes);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,jokesFragment).commit();
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.action_bar_reverse));
                    favJokes.setBackgroundColor(getColor(R.color.transparent));
                    jokes.setBackground(getDrawable(R.drawable.menu_wrapper));
                    isJokesActive=true;
                    sensorManager.registerListener(shakeDetector,sensor,SensorManager.SENSOR_DELAY_UI);
                }
                break;
            case R.id.fav_jokes:
                if(isJokesActive){
                    isJokesActive=false;
                    favJokesFragment=FavJokesFragment.newInstance(favJokesArray);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,favJokesFragment).commit();
                 //   getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.action_bar));
                    favJokes.setBackground(fav_jokes_drawable);
                    jokes.setBackgroundColor(getColor(R.color.transparent));
                    sensorManager.unregisterListener(shakeDetector);
                }else {
                    return;
                }
             break;
        }

    }

    @Override
    public void onShake(int count) {
        Collections.shuffle(staticJokes);
        jokesFragment=JokesFragment.newInstance(staticJokes);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,jokesFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeDetector,sensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shakeDetector);
    }
}