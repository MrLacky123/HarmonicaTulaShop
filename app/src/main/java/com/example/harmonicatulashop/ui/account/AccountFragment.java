package com.example.harmonicatulashop.ui.account;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private AccountViewModel viewModel;
    private FragmentAccountBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(MainActivity.Instance).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}