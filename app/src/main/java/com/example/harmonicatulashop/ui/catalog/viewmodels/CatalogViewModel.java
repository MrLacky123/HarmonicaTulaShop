package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.catalog.fragments.AccordionCatalogFragment;
import com.example.harmonicatulashop.ui.catalog.fragments.BayanCatalogFragment;
import com.example.harmonicatulashop.ui.catalog.fragments.HarmonicaCatalogFragment;

public class CatalogViewModel extends ViewModel {

    public void onClickHarmonica () {

        MainActivity.INSTANCE.setFragment(HarmonicaCatalogFragment.class, R.id.catalog_layout, null);

    }

    public void onClickBayan () {

        MainActivity.INSTANCE.setFragment(BayanCatalogFragment.class, R.id.catalog_layout, null);

    }

    public void onClickAccordion () {

        MainActivity.INSTANCE.setFragment(AccordionCatalogFragment.class, R.id.catalog_layout, null);

    }
}