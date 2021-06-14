package com.example.workdiary.work;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.workdiary.AppDatabase;
import com.example.workdiary.WorkHistoryModel;

import java.util.function.Consumer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class WorkHistoryViewModel extends ViewModel {
    private final AppDatabase database;

    @Inject
    public WorkHistoryViewModel(AppDatabase database) {
        this.database = database;
    }

    public void saveWork(String dateTime, Integer tag) {
        database.workHistoryDao().insertWorkHistory(
                new WorkHistoryModel(
                        dateTime,
                        tag
                )
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void getWorkHistory() {
        database.workHistoryDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result ->{
                            result.stream().forEach(list -> {
                                Log.e("data", list.getDateTime().toString());
                            });
                        }, failed -> {
                            Log.e("error", "error");
                        }
                );
    }
}
