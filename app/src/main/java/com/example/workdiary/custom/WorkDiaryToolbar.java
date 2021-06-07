package com.example.workdiary.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.workdiary.databinding.ToolbarWorkdiaryBinding;

import org.jetbrains.annotations.NotNull;

public class WorkDiaryToolbar extends ConstraintLayout {
    public WorkDiaryToolbar(@NonNull @NotNull Context context) {
        super(context);
    }

    public WorkDiaryToolbar(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WorkDiaryToolbar(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ToolbarWorkdiaryBinding binding = ToolbarWorkdiaryBinding.inflate(LayoutInflater.from(getContext()),this, true);


    public void setBackButton() {
        binding.btnBack.setVisibility(View.VISIBLE);
    }

    public void setToolbarTitle(String title) {
        binding.title.setText(title);
    }
}
