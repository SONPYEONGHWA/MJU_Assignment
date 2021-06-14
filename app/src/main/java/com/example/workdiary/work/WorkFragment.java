package com.example.workdiary.work;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.workdiary.WorkHistoryModel;
import com.example.workdiary.base.BaseFragment;
import com.example.workdiary.databinding.FragmentWorkBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

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
        stampWorkStart();
        getWorkHistory();

        viewModel.getHistories().observe(getViewLifecycleOwner(), new Observer<List<WorkHistoryModel>>() {
            @Override
            public void onChanged(List<WorkHistoryModel> histories) {
                histories.stream().forEach(new Consumer<WorkHistoryModel>() {
                    @Override
                    public void accept(WorkHistoryModel workHistoryModel) {
                        Log.e("data", "idx:" + workHistoryModel.getIndex() + "time:" + workHistoryModel.getDateTime() );
                    }
                });
            }
        });
    }

    private void stampWorkStart() {
        getBinding().btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.saveWork(getCurrentTime(), 0);
                getCurrentTime();
            }
        });
    }

    private void stampWorkEnd() {
        getBinding().btnLeaveWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        return dateFormat.format(date);
    }
}