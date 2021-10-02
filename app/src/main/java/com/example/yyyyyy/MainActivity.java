package com.example.yyyyyy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public TextView textView;
    private ImageButton likeButton;
    private Array cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        likeButton=findViewById(R.id.likeButton);

    }

    public Bitmap convertImageToBitmap(Context context, int drawable){
        Drawable drawable1= ContextCompat.getDrawable(context,drawable);
        Bitmap bitmap = Bitmap.createBitmap(drawable1.getIntrinsicWidth(),drawable1.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        drawable1.setBounds(0,canvas.getHeight(),canvas.getWidth(),0);
        drawable1.draw(canvas);
        return bitmap;
    }
}