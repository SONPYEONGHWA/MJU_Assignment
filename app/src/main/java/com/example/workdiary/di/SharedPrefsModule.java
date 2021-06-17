package com.example.workdiary.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.workdiary.util.SharedPrefsUtil;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class SharedPrefsModule {
    private static final String NAME_SHARED_PREFERENCES = "workdiary_prefs";

    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public static SharedPreferences.Editor editor(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }

    @Provides
    @Singleton
    public static SharedPrefsUtil provideSharedPrefs(SharedPreferences preferences, SharedPreferences.Editor editor){
        return new SharedPrefsUtil(preferences, editor);
    }

}
