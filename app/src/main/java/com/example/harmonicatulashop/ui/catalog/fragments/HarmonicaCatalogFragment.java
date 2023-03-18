package com.example.harmonicatulashop.ui.catalog.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.databinding.FragmentHarmonicaCatalogBinding;
import com.example.harmonicatulashop.ui.catalog.viewmodels.HarmonicaCatalogViewModel;

public class HarmonicaCatalogFragment extends Fragment {

    private FragmentHarmonicaCatalogBinding binding;
    private HarmonicaCatalogViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(HarmonicaCatalogViewModel.class);

        binding = FragmentHarmonicaCatalogBinding.inflate(inflater, container, false);
        binding.setViewmodel(viewModel);
        binding.executePendingBindings();

        return binding.getRoot();

    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}