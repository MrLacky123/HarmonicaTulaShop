package com.example.harmonicatulashop.ui.catalog;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.catalog.harmonica.HarmonicaCatalogFragment;

public class CatalogViewModel extends ViewModel {

    public ObservableField<Fragment> fragment = new ObservableField<>();

    public CatalogViewModel() {
        fragment.set(new CatalogFragment());
    }

    public void onClickHarmonica () {
        Log.d("RRR", "Harmonica Click");
    }

    public void onClickBayan () {
        Log.d("RRR", "Bayan Click");
    }

    public void onClickAccordion () {
        Log.d("RRR", "Accordion Click");
    }
}