package com.example.workdiary.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.workdiary.R;
import com.example.workdiary.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        initNavController();
    }

    private void initNavController() {
        NavHostFragment navHostFragment = new NavHostFragment();
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_nav_host);
        navHostFragment.getNavController();
    }
}