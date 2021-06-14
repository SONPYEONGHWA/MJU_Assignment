package com.example.workdiary.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.workdiary.R;
import com.example.workdiary.base.BaseFragment;
import com.example.workdiary.databinding.FragmentFrameBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FrameFragment extends BaseFragment<FragmentFrameBinding> {
    private FrameViewModel viewModel;

    @Override
    protected FragmentFrameBinding getFragmentBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentFrameBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FrameViewModel.class);
        setViewPagerAdapter();
        configureBottomNavigation();
        observePageIdx();

    }

    private void observePageIdx() {
        viewModel.getPageIdx().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                getBinding().viewpager.setCurrentItem(integer);
                selectBottomNavigationMenu(integer);
            }
        });
    }

    private void setViewPagerAdapter() {
        getBinding().viewpager.setAdapter(new ViewPagerAdapter(this));
        getBinding().viewpager.setOffscreenPageLimit(1);
        getBinding().viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewModel.setPageIdx(position);
                switch (position) {
                    case 0:
                        getBinding().bottomNav.setSelectedItemId(R.id.work);
                        viewModel.setPageIdx(position);
                        break;
                    case 1:
                        getBinding().bottomNav.setSelectedItemId(R.id.diary);
                        viewModel.setPageIdx(position);
                        break;
                }
            }
        });
    }

    private void configureBottomNavigation() {
        getBinding().bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.work:
                        getBinding().viewpager.setCurrentItem(0);
                        break;
                    case R.id.diary:
                        getBinding().viewpager.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });
    }


    private void selectBottomNavigationMenu(Integer pageIdx) {
        switch (pageIdx) {
            case 0:
                getBinding().bottomNav.setSelectedItemId(R.id.work);
                break;
            case 1:
                getBinding().bottomNav.setSelectedItemId(R.id.diary);
                break;
        }
    }
}