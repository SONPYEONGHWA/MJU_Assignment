package com.example.workdiary.work;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.workdiary.base.BaseFragment;
import com.example.workdiary.databinding.FragmentWorkBinding;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WorkFragment extends BaseFragment<FragmentWorkBinding> {
    private WorkHistoryViewModel viewModel;
    @Override
    protected FragmentWorkBinding getFragmentBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentWorkBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WorkHistoryViewModel.class);
        stampWork();
        getWorkHistory();
    }

    private void stampWork() {
        getBinding().btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.saveWork(getCurrentTime(), 0);
                getCurrentTime();
            }
        });
    }

    private void getWorkHistory() {
        getBinding().btnLeaveWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getWorkHistory();
            }
        });
    }

    private String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss");
        Log.e("current time", dateFormat.format(date).toString());
        return dateFormat.format(date);
    }
}