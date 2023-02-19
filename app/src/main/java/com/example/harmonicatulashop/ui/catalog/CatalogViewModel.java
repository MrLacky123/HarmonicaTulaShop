package com.example.harmonicatulashop.ui.catalog;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.google.android.material.snackbar.Snackbar;

public class CatalogViewModel extends ViewModel {

    public ObservableField<CatalogFragment> fragment = new ObservableField<>();

    public CatalogViewModel() {
        fragment.set(new CatalogFragment());
    }

    public void onClickHarmonica () {
        Snackbar.make(MainActivity.view, "Гармошки", Snackbar.LENGTH_LONG).show();
    }

    public void onClickBayan () {
        Snackbar.make(MainActivity.view, "Баяны", Snackbar.LENGTH_LONG).show();
    }

    public void onClickAccordion () {
        Snackbar.make(MainActivity.view, "Аккордеоны", Snackbar.LENGTH_LONG).show();
    }
}