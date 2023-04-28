package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.databinding.CatalogItemAccordionBinding;
import com.example.harmonicatulashop.models.catalog.Accordion;

public class AccordionViewModel extends ViewModel {

    public MutableLiveData<Accordion> accordion = new MutableLiveData<Accordion>();

    private CatalogItemAccordionBinding binding;

    public AccordionViewModel() {
        accordion.setValue(new Accordion());
    }

    public void addToCart() {

    }

    public void addToFavourites() {

    }
}
