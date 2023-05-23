package com.example.harmonicatulashop.ui.account.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentAccountAdminBinding;
import com.example.harmonicatulashop.databinding.FragmentAccountUserBinding;
import com.example.harmonicatulashop.ui.account.viewmodels.AccountUserViewModel;

public class AccountUserFragment extends Fragment {

    private AccountUserViewModel viewModel;
    private FragmentAccountUserBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(AccountUserViewModel.class);

        binding = FragmentAccountUserBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}
