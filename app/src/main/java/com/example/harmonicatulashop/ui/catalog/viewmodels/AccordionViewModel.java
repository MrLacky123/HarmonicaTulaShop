package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.databinding.ItemAccordionBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;

public class AccordionViewModel extends ViewModel {

    public MutableLiveData<Accordion> accordion = new MutableLiveData<Accordion>();

    private ItemAccordionBinding binding;

    public AccordionViewModel() {
        accordion.setValue(new Accordion());
    }

    public void addToCart() {

    }

    public void addToFavourites() {

    }
}
