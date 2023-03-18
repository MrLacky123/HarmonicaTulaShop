package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.graphics.drawable.Icon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.databinding.ItemHarmonicaBinding;
import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

public class HarmonicaViewModel extends ViewModel {

    public MutableLiveData<Harmonica> harmonica = new MutableLiveData<Harmonica>();

    private ItemHarmonicaBinding binding;

    public HarmonicaViewModel() {
        harmonica.setValue(new Harmonica());
    }

    public void addToCart() {

        binding.addToCart.setText("В корзине");

    }

    public void addToFavourites() {

        binding.addToFavourites.setImageIcon(Icon.createWithFilePath("D:\\HarmonicaTulaShop\\app\\src\\main\\res\\drawable\\baseline_favorite_24.xml"));

    }
}
