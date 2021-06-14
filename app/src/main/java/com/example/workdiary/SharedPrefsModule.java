package com.example.workdiary;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class SharedPrefsModule {

    @Provides
    @Singleton
    public static SharedPrefsUtil provideSharedPrefs(@ApplicationContext Context context){
        return new SharedPrefsUtil(context);
    }
}
