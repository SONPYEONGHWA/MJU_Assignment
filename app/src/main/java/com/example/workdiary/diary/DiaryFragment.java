package com.example.workdiary.diary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.workdiary.base.BaseFragment;
import com.example.workdiary.databinding.FragmentDiaryBinding;
import com.example.workdiary.util.SharedPrefsUtil;
import com.example.workdiary.util.VerticalItemDecoration;
import com.example.workdiary.work.WorkHistoryModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DiaryFragment extends BaseFragment<FragmentDiaryBinding> {
    private DiaryViewModel viewModel;
    private WorkHistoryAdapter adapter;
    @Inject
    public SharedPrefsUtil prefsUtil;


    @Override
    protected FragmentDiaryBinding getFragmentBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentDiaryBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DiaryViewModel.class);
        getBinding().setLifecycleOwner(getViewLifecycleOwner());
        viewModel.getUserName();
        viewModel.getWorkHistories();
        setWorkHistoryAdapter();
        fetchDatas();
        Log.e("daa", prefsUtil.getUserName("userName", "peace"));
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getUserName();
        viewModel.getWorkHistories();
    }

    public void setWorkHistoryAdapter() {
        getBinding().rvWorkhistory.addItemDecoration(new VerticalItemDecoration(14));
        adapter = new WorkHistoryAdapter(new WorkHistoryAdapter.WorkHistoryDiffUtil(), prefsUtil.getUserName("userName", "peace"));
        getBinding().rvWorkhistory.setAdapter(adapter);
    }

    public void fetchDatas() {
        viewModel.getHistories().observe(getViewLifecycleOwner(), new Observer<List<WorkHistoryModel>>() {
            @Override
            public void onChanged(List<WorkHistoryModel> workHistoryModels) {
                adapter.submitList(workHistoryModels);
            }
        });
    }
}