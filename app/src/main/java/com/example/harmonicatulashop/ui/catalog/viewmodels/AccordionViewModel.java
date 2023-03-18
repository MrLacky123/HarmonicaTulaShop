package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.graphics.drawable.Icon;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.databinding.ItemAccordionBinding;

public class AccordionViewModel extends ViewModel {

    private ItemAccordionBinding binding;

    public void addToCart() {

        binding.addToCart.setText("В корзине");

    }

    public void addToFavourites() {

        binding.addToFavourites.setImageIcon(Icon.createWithFilePath("D:\\HarmonicaTulaShop\\app\\src\\main\\res\\drawable\\baseline_favorite_24.xml"));

    }
}
