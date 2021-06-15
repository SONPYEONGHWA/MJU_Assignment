package com.example.workdiary.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.workdiary.R;
import com.example.workdiary.SharedPrefsUtil;
import com.example.workdiary.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;
    @Inject public SharedPrefsUtil prefs;

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

        if (prefs.getIsRegistered("isRegistered", false)) {
            navHostFragment.getNavController().navigate(R.id.action_registerFragment_to_frameFragment);
        }
    }
}