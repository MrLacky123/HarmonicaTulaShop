package com.example.harmonicatulashop.ui.catalog.harmonica;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class HarmonicaCatalogViewModel extends ViewModel {

    public ObservableField<HarmonicaCatalogFragment> fragment = new ObservableField<>();

    public HarmonicaCatalogViewModel() {
        fragment.set(new HarmonicaCatalogFragment());
    }
}