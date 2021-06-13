package com.example.workdiary;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workdiary.base.BaseFragment;
import com.example.workdiary.databinding.FragmentRegisterBinding;
import com.example.workdiary.databinding.FragmentRegisterDialogBinding;

import org.jetbrains.annotations.NotNull;


public class RegisterDialogFragment extends DialogFragment {
    FragmentRegisterDialogBinding binding = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        int height =(int) (getResources().getDisplayMetrics().heightPixels * 0.35);
        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.getRoot().setClipToOutline(true);
        closeDialog();
    }

    private void closeDialog() {
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}