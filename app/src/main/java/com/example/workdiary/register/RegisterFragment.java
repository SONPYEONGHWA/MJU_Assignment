package com.example.workdiary.register;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.workdiary.R;
import com.example.workdiary.base.BaseFragment;
import com.example.workdiary.databinding.FragmentRegisterBinding;


public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> {
    private RegisterViewModel viewModel;

    @Override
    protected FragmentRegisterBinding getFragmentBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentRegisterBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        getBinding().setViewModel(viewModel);
        getBinding().setLifecycleOwner(getViewLifecycleOwner());

        setToolbar();
        goToFrameFragment();
    }

    private void setToolbar() {
        getBinding().toolbar.setBackButton();
        getBinding().toolbar.setToolbarTitle("회사등록");
    }

    private void goToFrameFragment() {
        getBinding().btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("company name", viewModel.companyName.getValue().toString());
                Log.e("user name", viewModel.userName.getValue().toString());

                if (!viewModel.checkInput()) {
                    Navigation.findNavController(getBinding().getRoot()).navigate(R.id.action_registerFragment_to_frameFragment);
                } else {
//                    showDialog();
                }
            }
        });
    }
}