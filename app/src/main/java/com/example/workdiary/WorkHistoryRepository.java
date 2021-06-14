package com.example.workdiary;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WorkHistoryRepository {
    @Query("SELECT * FROM workHistory")
    List<WorkHistoryModel> getAll();
    
}
