package com.example.harmonicatulashop.ui.account.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentUserSettingsBinding;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.account.User;
import com.example.harmonicatulashop.ui.account.viewmodels.UserSettingsViewModel;

public class UserSettingsFragment extends Fragment {

    private UserSettingsViewModel viewModel;
    private FragmentUserSettingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MainActivity.currentLayout = R.id.user_settings_layout;

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(UserSettingsViewModel.class);

        binding = FragmentUserSettingsBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        bindView(MainActivity.currentUser);

        MainActivity.INSTANCE.getSupportActionBar();

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public void bindView(User user) {

        binding.editUserLogin.setText(user.getLogin());
        binding.editUserName.setText(user.getFirstname());
        binding.editUserLastname.setText(user.getLastname());
        binding.editUserAddress.setText(user.getAddress());

    }
}