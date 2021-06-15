package com.example.workdiary.work;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.workdiary.AppDatabase;
import com.example.workdiary.WorkHistoryModel;

import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class WorkHistoryViewModel extends ViewModel {
    private final AppDatabase database;

    @Inject
    public WorkHistoryViewModel(AppDatabase database) {
        this.database = database;
    }

    private final MutableLiveData<List<WorkHistoryModel>> histories = new MutableLiveData<List<WorkHistoryModel>>();
    public LiveData<List<WorkHistoryModel>> getHistories() {
        return histories;
    }

    private final MutableLiveData<String> userName = new MutableLiveData<String>();
    public final LiveData<String> getUserName() {
        return userName;
    }
    public void setUserName(String name) {
        userName.setValue(name);
    }

    private final  MutableLiveData<String> companyName = new MutableLiveData<String>();
    public final LiveData<String> getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String name) {
        companyName.setValue(name);
    }

    private final MutableLiveData<Uri> userImage = new MutableLiveData<Uri>();
    public final LiveData<Uri> getUserImage() {
        return userImage;
    }
    public void setUserImage(Uri image) {
        userImage.setValue(image);
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
                            histories.postValue(result);
//                            result.stream().forEach(list -> {
//                                Log.e("data", list.getDateTime().toString() + "tag" + list.getTag() + "index" + list.getIndex());
//                            });
                        }, failed -> {
                            Log.e("error", "error");
                        }
                );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
