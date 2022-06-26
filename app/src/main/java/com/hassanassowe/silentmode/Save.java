package com.hassanassowe.silentmode;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class Save extends AsyncTask<Void, Void, Void> {

    private final ArrayList<SilentMode> instance;
    private final Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Save(ArrayList<SilentMode> instance, Context context) {
        this.instance = instance;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... v) {
        sharedPreferences = context.getSharedPreferences("instanceKey", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(instance);
        editor.putString("list", json);
        editor.apply();
        return null;
    }
}
