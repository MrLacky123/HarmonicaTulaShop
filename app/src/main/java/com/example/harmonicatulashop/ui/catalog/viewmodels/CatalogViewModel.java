package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.catalog.fragments.AccordionCatalogFragment;
import com.example.harmonicatulashop.ui.catalog.fragments.BayanCatalogFragment;
import com.example.harmonicatulashop.ui.catalog.fragments.CatalogFragment;
import com.example.harmonicatulashop.ui.catalog.fragments.HarmonicaCatalogFragment;

public class CatalogViewModel extends ViewModel {

    public void onClickHarmonica () {

        MainActivity.Instance.setFragment(HarmonicaCatalogFragment.class, R.id.catalog_layout, null);

    }

    public void onClickBayan () {

        MainActivity.Instance.setFragment(BayanCatalogFragment.class, R.id.catalog_layout, null);

    }

    public void onClickAccordion () {

        MainActivity.Instance.setFragment(AccordionCatalogFragment.class, R.id.catalog_layout, null);

    }
}