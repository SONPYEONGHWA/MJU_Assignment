package com.example.workdiary.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FrameViewModel extends ViewModel {
    private final MutableLiveData<Integer> pageIdx = new MutableLiveData<Integer>();

    public MutableLiveData<Integer> getPageIdx() {
        return pageIdx;
    }

    public void setPageIdx(Integer idx) {
        pageIdx.setValue(idx);
    }
}
