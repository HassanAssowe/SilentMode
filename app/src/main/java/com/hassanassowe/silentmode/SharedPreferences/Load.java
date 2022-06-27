package com.hassanassowe.silentmode.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hassanassowe.silentmode.SilentMode;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Load extends AsyncTask<Void, Void, Void> {

    private final ArrayList<SilentMode> instance;
    private final Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Load(ArrayList<SilentMode> instance, Context context) {
        this.instance = instance;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... v) {
        sharedPreferences = context.getSharedPreferences("instanceKey", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<SilentMode>>() {
        }.getType();
        ArrayList<SilentMode> load = gson.fromJson(json, type);
        if (load != null) {
            instance.addAll(load);
        }
        return null;
    }
}