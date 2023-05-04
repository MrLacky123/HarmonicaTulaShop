package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.ActivityAccordionBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.ui.catalog.activities.AccordionActivity;

public class AccordionActivityViewModel extends ViewModel {

    private ActivityAccordionBinding binding;
    private Accordion accordion;

    public void setBinding(ActivityAccordionBinding binding) {
        this.binding = binding;
    }

    public void setAccordion(Accordion accordion) {
        this.accordion = accordion;
    }

    public void addToCart() {
        CartRepository cartRepository = new CartRepository(AccordionActivity.INSTANCE.getApplication());
        cartRepository.insertAccordion(accordion);

        binding.addAccordionToCart.setText("В корзине");

        close();
    }

    public void addToFavourites() {
        FavouriteRepository favouriteRepository = new FavouriteRepository(AccordionActivity.INSTANCE.getApplication());
        favouriteRepository.insertAccordion(accordion);

        binding.addAccordionToFavourites.setText("В отложенных");
    }

    public void close() {
        AccordionActivity.INSTANCE.finish();
    }
}
