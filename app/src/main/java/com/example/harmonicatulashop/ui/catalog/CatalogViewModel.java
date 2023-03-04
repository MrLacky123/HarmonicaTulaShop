package com.example.harmonicatulashop.ui.catalog;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.Harmonica;
import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.ui.catalog.harmonica.HarmonicaCatalogFragment;

public class CatalogViewModel extends ViewModel {

    public ObservableField<CatalogFragment> fragment = new ObservableField<>();

    public CatalogViewModel() {
        fragment.set(new CatalogFragment());
    }

    public void onClickHarmonica () {
        Log.d("RRR", "Harmonica Click");

        Bundle args = new Bundle();

        args.putParcelable("Harmonica", new Harmonica());
        MainActivity.Instance.setFragment(HarmonicaCatalogFragment.class, args);
    }

    public void onClickBayan () {
        Log.d("RRR", "Bayan Click");
    }

    public void onClickAccordion () {
        Log.d("RRR", "Accordion Click");
    }
}