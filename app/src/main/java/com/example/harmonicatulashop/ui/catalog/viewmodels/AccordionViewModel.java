package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.graphics.drawable.Icon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.databinding.ItemAccordionBinding;
import com.example.harmonicatulashop.ui.models.Accordion;

public class AccordionViewModel extends ViewModel {

    public MutableLiveData<Accordion> accordion = new MutableLiveData<Accordion>();

    private ItemAccordionBinding binding;

    public AccordionViewModel() {
        accordion.setValue(new Accordion());
    }

    public void addToCart() {

        binding.addToCart.setText("В корзине");

    }

    public void addToFavourites() {

        binding.addToFavourites.setImageIcon(Icon.createWithFilePath("D:\\HarmonicaTulaShop\\app\\src\\main\\res\\drawable\\baseline_favorite_24.xml"));

    }
}
