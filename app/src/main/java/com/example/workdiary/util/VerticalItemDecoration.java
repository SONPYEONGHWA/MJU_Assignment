package com.example.workdiary.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class VerticalItemDecoration extends RecyclerView.ItemDecoration {
    private Integer divHeight;
    public VerticalItemDecoration(Integer divHeight) {
        this.divHeight = divHeight;
    }

    @Override
    public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
        outRect.top = divHeight;
        outRect.bottom = divHeight;
    }
}
