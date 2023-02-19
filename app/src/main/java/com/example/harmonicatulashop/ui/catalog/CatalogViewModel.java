package com.example.harmonicatulashop.ui.catalog;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class CatalogViewModel extends ViewModel {

    public ObservableField<CatalogFragment> fragment = new ObservableField<>();

    public CatalogViewModel() {
        fragment.set(new CatalogFragment());
    }

    public void onClickHarmonica () {
        Log.d("RRRR", "Harmonica Click");
    }

    public void onClickBayan () {
        Log.d("RRRR", "Bayan Click");
    }

    public void onClickAccordion () {
        Log.d("RRRR", "Accordion Click");
    }
}