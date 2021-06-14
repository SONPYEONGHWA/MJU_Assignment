package com.example.workdiary;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workHistory")
public class WorkHistoryModel {
    @PrimaryKey(autoGenerate = true)
    private int index;
    private String startTime;
    private String endTime;

    public WorkHistoryModel(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
