package com.hassanassowe.silentmode;
/*
This class is responsible for the loading and saving of registering Raspberry Pi devices.
 */
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Save_LoadData {
    public static void saveData(Context context, ArrayList<SilentMode> instance) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("instanceKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(instance);
        editor.putString("list", json);
        editor.apply();
    }

    public static void loadData(Context context, ArrayList<SilentMode> instance) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("instanceKey", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<SilentMode>>() {
        }.getType();
        ArrayList<SilentMode> load = gson.fromJson(json, type);
        if (load != null) {
            instance.addAll(load);
        }
    }
}

