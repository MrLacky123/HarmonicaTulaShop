package com.example.harmonicatulashop.ui.catalog.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentCatalogBinding;
import com.example.harmonicatulashop.ui.catalog.viewmodels.CatalogViewModel;

public class CatalogFragment extends Fragment {

    private FragmentCatalogBinding binding;
    private CatalogViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.previousTitles.clear();
        MainActivity.previousFragmentMap.clear();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(CatalogViewModel.class);

        binding = FragmentCatalogBinding.inflate(inflater, container, false);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        binding.catalogHarmonicaImage.setClipToOutline(true);
        binding.catalogBayanImage.setClipToOutline(true);
        binding.catalogAccordionImage.setClipToOutline(true);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        ActionBar actionBar = MainActivity.INSTANCE.getSupportActionBar();

        assert actionBar != null;
        actionBar.setTitle(R.string.title_catalog);

        actionBar.setDisplayHomeAsUpEnabled(false);
    }
}