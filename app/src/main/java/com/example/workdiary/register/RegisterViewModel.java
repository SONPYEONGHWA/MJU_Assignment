package com.example.workdiary.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.workdiary.util.InputChecker;
import java.util.ArrayList;

public class RegisterViewModel extends ViewModel {
    public MutableLiveData<String> companyName = new MutableLiveData<String>();
    public MutableLiveData<String> userName = new MutableLiveData<String>();

}
