package com.example.yyyyyy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentContainerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ParserTask.JSONDataLoaded,JokesAdapter.JokeIsLiked {
    private FragmentContainerView fragmentContainerView;
    private JokesFragment jokesFragment;
    private ArrayList<Joke> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentContainerView=findViewById(R.id.fragmentContainer);
        new FetchTask(this).execute();
       // jokesFragment=JokesFragment.newInstance(arrayList);
      //  Log.i("ARR",String.valueOf(arrayList));
      //  getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,jokesFragment).commit();

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
      jokesFragment=JokesFragment.newInstance(jokes);
     getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,jokesFragment).commit();
    }

    @Override
    public void hasLikedJoke(Joke joke) {

    }


}