package com.example.workdiary;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workHistory")
public class WorkHistoryModel {
    @PrimaryKey(autoGenerate = true)
    private int index;
    private String dateTime;
    private String workTime = " ";
    private Integer tag;

    public WorkHistoryModel(String dateTime, String workTime, Integer tag) {
        this.dateTime = dateTime;
        this.workTime = workTime;
        this.tag = tag;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDateTime() { return dateTime; }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getWorkTime() {return workTime; }

    public void setWorkTime(String workTime) { this.workTime = workTime; }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }
}
