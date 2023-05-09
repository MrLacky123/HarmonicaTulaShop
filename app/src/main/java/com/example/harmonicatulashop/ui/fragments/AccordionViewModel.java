package com.example.harmonicatulashop.ui.fragments;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentAccordionBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;

public class AccordionViewModel extends ViewModel {

    private FragmentAccordionBinding binding;
    private Accordion accordion;

    public void setBinding(FragmentAccordionBinding binding) {
        this.binding = binding;
    }

    public void setAccordion(Accordion accordion) {
        this.accordion = accordion;
    }

    public void addToCart() {
        CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
        cartRepository.insertAccordion(accordion);

        binding.addAccordionToCart.setText("В корзине");
    }

    public void addToFavourites() {
        FavouriteRepository favouriteRepository = new FavouriteRepository(MainActivity.INSTANCE.getApplication());
        favouriteRepository.insertAccordion(accordion);

        binding.addAccordionToFavourites.setText("В отложенных");
    }
}