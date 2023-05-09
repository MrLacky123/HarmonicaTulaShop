package com.example.harmonicatulashop.ui.fragments;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentHarmonicaBinding;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

public class HarmonicaViewModel extends ViewModel {

    private FragmentHarmonicaBinding binding;
    private Harmonica harmonica;

    public void setBinding(FragmentHarmonicaBinding binding) {
        this.binding = binding;
    }

    public void setHarmonica(Harmonica harmonica) {
        this.harmonica = harmonica;
    }

    public void addToCart() {
        CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
        cartRepository.insertHarmonica(harmonica);

        binding.addHarmonicaToCart.setText("В корзине");
    }

    public void addToFavourites() {
        FavouriteRepository favouriteRepository = new FavouriteRepository(MainActivity.INSTANCE.getApplication());
        favouriteRepository.insertHarmonica(harmonica);

        binding.addHarmonicaToFavourites.setText("В отложенных");
    }
}