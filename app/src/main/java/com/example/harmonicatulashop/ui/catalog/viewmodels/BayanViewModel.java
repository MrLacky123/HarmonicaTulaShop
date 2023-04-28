package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.databinding.CatalogItemBayanBinding;
import com.example.harmonicatulashop.models.catalog.Bayan;

public class BayanViewModel extends ViewModel {

    public MutableLiveData<Bayan> bayan = new MutableLiveData<Bayan>();

    private CatalogItemBayanBinding binding;

    public BayanViewModel() {
        bayan.setValue(new Bayan());
    }

    public void addToCart() {



    }

    public void addToFavourites() {



    }
}
