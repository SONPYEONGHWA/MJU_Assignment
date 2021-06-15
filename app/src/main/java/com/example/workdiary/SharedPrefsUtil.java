package com.example.workdiary;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class SharedPrefsUtil {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Inject
    public SharedPrefsUtil(
            SharedPreferences preferences,
            SharedPreferences.Editor editor) {
        this.preferences = preferences;
        this.editor = editor;
    }


    public void setString(String key, String value) {
        editor.putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public void setInteger(String key, Integer value) {
        editor.putInt(key, value).apply();
    }

    public Integer getInteger(String key, Integer defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public void setBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public void setUri(String key, Uri value) {
        setString(key, value.toString());
    }

    public Uri getUri(String key, String defaultValue) {
        return Uri.parse(preferences.getString(key, defaultValue));
    }

    public void setUserName(String key, String value) {
        setString(key, value);
    }

    public String getUserName(String key, String defaultValue) {
        return getString(key, defaultValue);
    }

    public void setCompanyName(String key, String value) {
        setString(key, value);
    }

    public String getCompanyName(String key, String defaultValue) {
        return getString(key, defaultValue);
    }

    public void setUserImage(String key, Uri value) {
        setUri(key,value);
    }

    public Uri getUserImage(String key, String defaultValue) {
        return getUri(key, defaultValue);
    }

    public void setIsRegistered(String key, Boolean value) {
        setBoolean(key, value);
    }

    public Boolean getIsRegistered(String key, Boolean defaultValue) {
        return getBoolean(key, defaultValue);
    }
}
