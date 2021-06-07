package com.example.workdiary.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<T extends ViewBinding> extends Fragment {
    private T binding;
    public T getBinding() {
        return binding;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = getFragmentBinding(inflater, container);
        return getBinding().getRoot();
    }

    protected abstract T getFragmentBinding(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
