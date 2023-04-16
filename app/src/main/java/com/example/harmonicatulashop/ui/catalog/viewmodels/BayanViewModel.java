package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.databinding.ItemBayanBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;

public class BayanViewModel extends ViewModel {

    public MutableLiveData<Bayan> bayan = new MutableLiveData<Bayan>();

    private ItemBayanBinding binding;

    public BayanViewModel() {
        bayan.setValue(new Bayan());
    }

    public void addToCart() {



    }

    public void addToFavourites() {



    }
}
