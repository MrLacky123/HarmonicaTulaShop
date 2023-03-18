package com.example.harmonicatulashop.ui.catalog.fragments;

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
import com.example.harmonicatulashop.databinding.FragmentBayanCatalogBinding;
import com.example.harmonicatulashop.ui.catalog.viewmodels.BayanCatalogViewModel;

public class BayanCatalogFragment extends Fragment {

    private BayanCatalogViewModel viewModel;
    private FragmentBayanCatalogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(MainActivity.Instance).get(BayanCatalogViewModel.class);

        binding = FragmentBayanCatalogBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        return binding.getRoot();
    }
}