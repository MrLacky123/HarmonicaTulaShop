package com.example.harmonicatulashop.ui.catalog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.databinding.FragmentCatalogBinding;

public class CatalogFragment extends Fragment {

    private FragmentCatalogBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CatalogViewModel catalogViewModel = new ViewModelProvider(this).get(CatalogViewModel.class);

        binding = FragmentCatalogBinding.inflate(inflater, container, false);
        binding.setViewmodel(catalogViewModel);
        binding.executePendingBindings();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}