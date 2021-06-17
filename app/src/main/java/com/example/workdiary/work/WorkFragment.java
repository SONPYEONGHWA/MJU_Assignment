package com.example.workdiary.work;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.workdiary.util.SharedPrefsUtil;
import com.example.workdiary.base.BaseFragment;
import com.example.workdiary.databinding.FragmentWorkBinding;
import com.example.workdiary.util.DateUtil;

import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.workdiary.diary.WorkHistoryAdapter.ViewType.END_TIME;
import static com.example.workdiary.diary.WorkHistoryAdapter.ViewType.START_TIME;

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
        setUserInfo();
        stampWorkEnd();

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
                viewModel.saveWork(currentTime,START_TIME, null);
                Toast.makeText(requireContext(), "출근하셨네요! 오늘도 화이팅:)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void stampWorkEnd() {
        getBinding().btnLeaveWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startTime = prefs.getStartTime("startTime", "");
                String endTime = dateUtil.getCurrentTime();
                String workTime = dateUtil.diffDate(startTime, endTime);
                viewModel.saveWork(endTime, END_TIME, workTime);
                Toast.makeText(requireContext(), "기분좋은 퇴근 야호~!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}