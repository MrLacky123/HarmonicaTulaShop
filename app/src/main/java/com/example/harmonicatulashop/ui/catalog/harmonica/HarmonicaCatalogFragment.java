package com.example.harmonicatulashop.ui.catalog.harmonica;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentCatalogBinding;

public class HarmonicaCatalogFragment extends Fragment {

    private HarmonicaCatalogViewModel mViewModel;

    public static HarmonicaCatalogFragment newInstance() {
        return new HarmonicaCatalogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_harmonica_catalog, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HarmonicaCatalogViewModel.class);
        // TODO: Use the ViewModel
    }
}