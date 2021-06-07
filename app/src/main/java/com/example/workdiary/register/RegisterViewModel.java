package com.example.workdiary.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.workdiary.util.InputChecker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterViewModel extends ViewModel {
    public MutableLiveData<String> companyName = new MutableLiveData<String>("");
    public MutableLiveData<String> userName = new MutableLiveData<String>("");


    public boolean checkInput() {
        InputChecker inputChecker = InputChecker.getInstance();
        List<String> textList = Arrays.asList(companyName.getValue(), userName.getValue());
        return inputChecker.checkEmptyString(textList);
    }
}
