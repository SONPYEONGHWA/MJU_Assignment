package com.example.workdiary.di;

import android.content.Context;

import com.example.workdiary.AppDatabase;
import com.example.workdiary.WorkHistoryDao;
import com.example.workdiary.util.DateUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class WorkHistoryModule {
    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return AppDatabase.getInstance(context);
    }

    @Provides
    @Singleton
    public static DateUtil provideDateUtil() {
        return new DateUtil();
    }
}
