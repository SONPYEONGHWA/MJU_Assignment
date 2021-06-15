package com.example.workdiary.register;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.workdiary.util.InputChecker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

public class RegisterViewModel extends ViewModel {
    private final MutableLiveData<String> companyName = new MutableLiveData<String>("");
    public void setCompanyName(String name) {
        companyName.setValue(name);
    }
    public MutableLiveData<String> getCompanyName() {
        return companyName;
    }

    private final MutableLiveData<String> userName = new MutableLiveData<String>("");
    public void setUserName(String name){
        userName.setValue(name);
    }
    public MutableLiveData<String> getUserName() {
        return userName;
    }


    private final MutableLiveData<Uri> profileUri = new MutableLiveData<Uri>();
    public MutableLiveData<Uri> getProfileUri() {
        return profileUri;
    }
    public void setProfileUri(Uri uri) {
        profileUri.setValue(uri);
    }

    public boolean checkInput() {
        List<String> textList = Arrays.asList(companyName.getValue(), userName.getValue());
        InputChecker.getInstance();
        return InputChecker.checkEmptyString(textList);
    }
}
