package com.example.workdiary.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.workdiary.work.WorkHistoryModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface WorkHistoryDao {
    @Query("SELECT * FROM workHistory ORDER BY workHistory.dateTime DESC")
    Single<List<WorkHistoryModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertWorkHistory(WorkHistoryModel workHistoryModel);
}
