package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.ui.catalog.fragments.HarmonicaCatalogFragment;

public class HarmonicaCatalogViewModel extends ViewModel {

    public ObservableField<HarmonicaCatalogFragment> fragment = new ObservableField<>();

    public HarmonicaCatalogViewModel() {
        fragment.set(new HarmonicaCatalogFragment());
    }
}