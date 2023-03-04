package com.example.harmonicatulashop.ui.catalog.harmonica;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.catalog.CatalogFragment;

public class HarmonicaCatalogViewModel extends ViewModel {

    public ObservableField<HarmonicaCatalogFragment> fragment = new ObservableField<>();

    public HarmonicaCatalogViewModel() {
        fragment.set(new HarmonicaCatalogFragment());
    }

    public void goBack() {

        MainActivity.Instance.removeFragment(HarmonicaCatalogFragment.class);

    }
}