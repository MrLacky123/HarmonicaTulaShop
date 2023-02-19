package com.example.harmonicatulashop.ui.catalog.harmonica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.databinding.FragmentHarmonicaCatalogBinding;

public class HarmonicaCatalogFragment extends Fragment {

    private FragmentHarmonicaCatalogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        HarmonicaCatalogViewModel viewModel = new ViewModelProvider(this).get(HarmonicaCatalogViewModel.class);

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