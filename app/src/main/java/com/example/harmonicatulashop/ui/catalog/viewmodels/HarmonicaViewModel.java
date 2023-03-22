package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.graphics.drawable.Icon;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
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
