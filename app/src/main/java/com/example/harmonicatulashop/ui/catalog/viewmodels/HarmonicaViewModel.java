package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

public class HarmonicaViewModel extends ViewModel {

    public MutableLiveData<Harmonica> harmonica = new MutableLiveData<Harmonica>();

    public HarmonicaViewModel() {
        harmonica.setValue(new Harmonica());
    }

    public void addToCart() {

    }

    public void addToFavourites() {

    }
}
