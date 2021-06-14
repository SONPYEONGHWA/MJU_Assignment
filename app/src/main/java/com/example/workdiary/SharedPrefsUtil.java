package com.example.workdiary;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class SharedPrefsUtil {
    private Context context;
    private static final String NAME_SHARED_PREFERENCES = "workdiary_prefs";

    @Inject
    public SharedPrefsUtil(Context context) {
        this.context = context;
    }

    private final SharedPreferences prefs = context.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    private final SharedPreferences.Editor editor = prefs.edit();

    public void setString(String key, String value) {
        editor.putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
    }

    public void setInteger(String key, Integer value) {
        editor.putInt(key,value).apply();
    }

    public Integer getInteger(String key, Integer defaultValue) {
        return prefs.getInt(key, defaultValue);
    }
}
