package com.example.yyyyyy;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FetchTask extends AsyncTask<Void,Void,String> implements ParserTask.JSONDataLoaded{
    private Context context;
    public FetchTask (Context context){
        this.context=context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String json;
        try {
            InputStream inputStream= context.getAssets().open("jokes.json");
            int size = inputStream.available();
            byte[] bytes= new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            json=new String(bytes,"UTF-8");

        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);
        new ParserTask((ParserTask.JSONDataLoaded) context).execute(json);
    }


    @Override
    public void JSONDateReady(ArrayList<Joke> jokes) {

    }
}
