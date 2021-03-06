package com.example.workdiary.register;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.workdiary.R;
import com.example.workdiary.util.SharedPrefsUtil;
import com.example.workdiary.base.BaseFragment;
import com.example.workdiary.databinding.FragmentRegisterBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> {
    private RegisterViewModel viewModel;
    public static final String PERMISSION_GALLERY = Manifest.permission.READ_EXTERNAL_STORAGE;
    @Inject public SharedPrefsUtil prefs;

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
        getBinding().btnAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });
    }

    private void setToolbar() {
        getBinding().toolbar.setBackButton();
        getBinding().toolbar.setToolbarTitle("?????? ??????");
    }

    private void goToFrameFragment() {
        getBinding().btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!viewModel.checkInput()) {
                    prefs.setIsRegistered("isRegistered", true);
                    prefs.setUserName("userName", viewModel.getUserName().getValue());
                    prefs.setCompanyName("companyName", viewModel.getCompanyName().getValue());
                    if (viewModel.getProfileUri().getValue() != null) {
                        prefs.setUserImage("userImage", viewModel.getProfileUri().getValue());
                    }
                    Navigation.findNavController(getBinding().getRoot()).navigate(R.id.action_registerFragment_to_frameFragment);
                } else {
                    showDialog();
                }
            }
        });
    }

    private void showDialog() {
        RegisterDialogFragment dialog = new RegisterDialogFragment();
        dialog.show(getChildFragmentManager(), getTag());
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                PERMISSION_GALLERY) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setType("image/*");
            getProfileImage.launch(intent);
        } else  {
            requestPermissionLauncher.launch(PERMISSION_GALLERY);
        }
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    checkPermissions();
                } else {
                    Toast.makeText(requireContext(), "????????? ??????????????? ??????????????????", Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher getProfileImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->  {
        if (result.getData() != null) {
            viewModel.setProfileUri(result.getData().getData());
        }
            });
}