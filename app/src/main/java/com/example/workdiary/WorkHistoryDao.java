package com.example.workdiary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface WorkHistoryDao {
    @Query("SELECT * FROM workHistory ORDER BY workHistory.dateTime ASC")
    Single<List<WorkHistoryModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertWorkHistory(WorkHistoryModel workHistoryModel);
}
