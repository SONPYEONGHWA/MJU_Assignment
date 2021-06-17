package com.example.workdiary.diary;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workdiary.db.AppDatabase;
import com.example.workdiary.util.SharedPrefsUtil;
import com.example.workdiary.work.WorkHistoryModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class DiaryViewModel extends ViewModel {
    private final AppDatabase database;
    private final SharedPrefsUtil prefsUtil;

    @Inject
    public DiaryViewModel(AppDatabase database, SharedPrefsUtil prefsUtil) {
        this.database = database;
        this.prefsUtil = prefsUtil;
    }

    private final MutableLiveData<String> userName = new MutableLiveData<String>();
    public MutableLiveData<String> getUserName() {
        return userName;
    }
    public void setUserName() {
        userName.setValue(prefsUtil.getUserName("userName", "손평화"));
    }

    private final MutableLiveData<List<WorkHistoryModel>> histories = new MutableLiveData<List<WorkHistoryModel>>();
    public LiveData<List<WorkHistoryModel>> getHistories() { return histories; }

    public void getWorkHistories() {
        database.workHistoryDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        histories::setValue,
                        error -> {
                            Log.e("error", error.toString());
                });
    }

}
