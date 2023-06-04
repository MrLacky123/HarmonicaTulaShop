package com.example.harmonicatulashop.ui.fragments;

import static com.example.harmonicatulashop.models.harmonica.Accordion.ICON;
import static com.example.harmonicatulashop.models.harmonica.Accordion.ID;
import static com.example.harmonicatulashop.models.harmonica.Accordion.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Accordion.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Accordion.RANGE;

import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentAccordionBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.ui.account.fragments.AddAccordionFragment;
import com.example.harmonicatulashop.ui.account.fragments.AddHarmonicaFragment;

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

        if (MainActivity.currentAdmin != null) {

            new CatalogRepository(MainActivity.INSTANCE.getApplication()).deleteAccordion(accordion);
            return;
        }

        CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
        cartRepository.insertAccordion(accordion);

        binding.addAccordionToCart.setText("В корзине");
    }

    public void addToFavourites() {

        if (MainActivity.currentAdmin != null) {

            Bundle bundle = new Bundle();

            bundle.putInt(ID, accordion.getId());
            bundle.putByteArray(ICON, accordion.getIcon());
            bundle.putString(RANGE, accordion.getRange());
            bundle.putInt(PRICE, accordion.getPrice());
            bundle.putString(OPTIONS, accordion.getOptions());

            MainActivity.INSTANCE.setFragment(AddAccordionFragment.class,
                    MainActivity.currentLayout, bundle,
                    "Редактировать аккордеон",
                    AccordionFragment.class);

            return;
        } else if (MainActivity.currentUser == null) {

            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Войдите, чтобы добавить инструмент в отложенное", Toast.LENGTH_SHORT).show();
            return;
        }

        FavouriteRepository favouriteRepository = new FavouriteRepository(MainActivity.INSTANCE.getApplication());
        favouriteRepository.insertAccordion(accordion);

        binding.addAccordionToFavourites.setText("В отложенных");
    }
}