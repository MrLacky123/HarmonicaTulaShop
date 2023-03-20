package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.graphics.drawable.Icon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.databinding.ItemBayanBinding;
import com.example.harmonicatulashop.ui.catalog.models.Bayan;

public class BayanViewModel extends ViewModel {

    public MutableLiveData<Bayan> bayan = new MutableLiveData<Bayan>();

    private ItemBayanBinding binding;

    public BayanViewModel() {
        bayan.setValue(new Bayan());
    }

    public void addToCart() {

        binding.addToCart.setText("В корзине");

    }

    public void addToFavourites() {

        binding.addToFavourites.setImageIcon(Icon.createWithFilePath("D:\\HarmonicaTulaShop\\app\\src\\main\\res\\drawable\\baseline_favorite_24.xml"));

    }
}
