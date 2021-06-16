package com.example.workdiary.work;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.workdiary.SharedPrefsUtil;
import com.example.workdiary.WorkHistoryModel;
import com.example.workdiary.base.BaseFragment;
import com.example.workdiary.databinding.FragmentWorkBinding;
import com.example.workdiary.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WorkFragment extends BaseFragment<FragmentWorkBinding> {
    private WorkHistoryViewModel viewModel;
    @Inject public SharedPrefsUtil prefs;
    @Inject public DateUtil dateUtil;

    @Override
    protected FragmentWorkBinding getFragmentBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentWorkBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WorkHistoryViewModel.class);
        getBinding().setViewModel(viewModel);
        getBinding().setLifecycleOwner(getViewLifecycleOwner());

        stampWorkStart();
        getWorkHistory();
        setUserInfo();

        viewModel.getHistories().observe(getViewLifecycleOwner(), new Observer<List<WorkHistoryModel>>() {
            @Override
            public void onChanged(List<WorkHistoryModel> histories) {
                histories.stream().forEach(new Consumer<WorkHistoryModel>() {
                    @Override
                    public void accept(WorkHistoryModel workHistoryModel) {
                        Log.e("data", "idx:" + workHistoryModel.getIndex() + "time:" + workHistoryModel.getDateTime() + "workTime:" + workHistoryModel.getWorkTime() );
                    }
                });
            }
        });
    }

    private void setUserInfo() {
        viewModel.setCompanyName(prefs.getCompanyName("companyName", "회사정보 없음"));
        viewModel.setUserName(prefs.getUserName("userName", "유저정보 없음"));
        viewModel.setUserImage(prefs.getUserImage("userImage", "사진 정보 없음"));
    }

    private void stampWorkStart() {
        getBinding().btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentTime = dateUtil.getCurrentTime();
                prefs.setStartTime("startTime", currentTime);
                viewModel.saveWork(currentTime,0, null);
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
                String startTime = prefs.getStartTime("startTime", "");
                String endTime = dateUtil.getCurrentTime();
                String workTime = dateUtil.diffDate(startTime, endTime);
                Log.e("workTime:",workTime);
                viewModel.saveWork(endTime, 1, workTime);
            }
        });

        getBinding().ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getWorkHistory();
            }
        });
    }

//    private String getCurrentTime() {
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss");
//        return dateFormat.format(date);
//    }
//
//    private String diffDate(String startTime, String endTime) {
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss");
//        String workTime = "";
//        try {
//            Date start = dateFormat.parse(startTime);
//            Date end = dateFormat.parse(endTime);
//            long diff = end.getTime() - start.getTime();
//            long seconds = diff / 1000;
//            long minutes = seconds / 60;
//            long hours = minutes / 60;
//            long days = hours / 24;
//            workTime = days + "일" + hours%24 + "시간" + minutes%60 + "분" + seconds%60 + "초";
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return workTime;
//    }
}