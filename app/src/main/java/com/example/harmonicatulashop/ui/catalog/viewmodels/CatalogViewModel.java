package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.catalog.fragments.CatalogFragment;
import com.example.harmonicatulashop.ui.catalog.fragments.HarmonicaCatalogFragment;

public class CatalogViewModel extends ViewModel {

    public void onClickHarmonica () {
        Log.d("RRR", "Harmonica Click");

        Bundle args = new Bundle();

        //args.putParcelable("Harmonica", new Harmonica());
        MainActivity.Instance.setFragment(HarmonicaCatalogFragment.class, R.id.catalog_layout, args);
    }

    public void onClickBayan () {
        Log.d("RRR", "Bayan Click");
    }

    public void onClickAccordion () {
        Log.d("RRR", "Accordion Click");
    }
}