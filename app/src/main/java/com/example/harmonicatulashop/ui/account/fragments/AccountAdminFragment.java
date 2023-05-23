package com.example.harmonicatulashop.ui.account.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.databinding.FragmentAccountAdminBinding;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.ui.account.viewmodels.AccountAdminViewModel;

public class AccountAdminFragment extends Fragment {

    private AccountAdminViewModel viewModel;
    private FragmentAccountAdminBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(AccountAdminViewModel.class);

        binding = FragmentAccountAdminBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.setBinding(binding);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onStart() {
        bindView(MainActivity.currentAdmin);
        super.onStart();
    }

    public void bindView(Admin admin) {
        if (admin == null) {
            return;
        }

        binding.loginAdmin.setText(admin.getLogin());
        binding.nameAdmin.setText(admin.getFirstname());
        binding.lastnameAdmin.setText(admin.getLastname());

        byte[] image = admin.getAvatarImage();

        if (image == null) {
            return;
        }
        binding.profileImageAdmin.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));

    }
}