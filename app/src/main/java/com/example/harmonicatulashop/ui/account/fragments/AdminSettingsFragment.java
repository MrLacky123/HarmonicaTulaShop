package com.example.harmonicatulashop.ui.account.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentAdminSettingsBinding;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.ui.account.viewmodels.AdminSettingsViewModel;

public class AdminSettingsFragment extends Fragment {

    private AdminSettingsViewModel viewModel;
    private FragmentAdminSettingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(AdminSettingsViewModel.class);

        binding = FragmentAdminSettingsBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.setBinding(binding);

        bindView(MainActivity.currentAdmin);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void bindView(Admin admin) {

        binding.editAdminLogin.setText(admin.getLogin());
        binding.editAdminName.setText(admin.getFirstname());
        binding.editAdminLastname.setText(admin.getLastname());

    }
}