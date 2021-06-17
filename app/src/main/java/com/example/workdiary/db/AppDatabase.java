package com.example.workdiary.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.workdiary.work.WorkHistoryModel;

import org.jetbrains.annotations.NotNull;

@Database(entities = {WorkHistoryModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WorkHistoryDao workHistoryDao();
    private static final String NAME_DATABASE = "Work Diary";
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        return INSTANCE != null ? INSTANCE : buildDatabase(context);
    }

    private static AppDatabase buildDatabase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, NAME_DATABASE)
                .fallbackToDestructiveMigration()
                .addCallback(new RoomDatabase.Callback(){
                    @Override
                    public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }
                }).build();
    }
}
