package com.example.workdiary;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WorkHistoryModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WorkHistoryRepository workHistoryRepository();
}
