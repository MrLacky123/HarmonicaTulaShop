package com.example.harmonicatulashop.ui.fragments;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentBayanBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;

public class BayanViewModel extends ViewModel {

    private FragmentBayanBinding binding;
    private Bayan bayan;

    public void setBinding(FragmentBayanBinding binding) {
        this.binding = binding;
    }

    public void setBayan(Bayan bayan) {
        this.bayan = bayan;
    }

    public void addToCart() {
        CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
        cartRepository.insertBayan(bayan);

        binding.addBayanToCart.setText("В корзине");
    }

    public void addToFavourites() {
        FavouriteRepository favouriteRepository = new FavouriteRepository(MainActivity.INSTANCE.getApplication());
        favouriteRepository.insertBayan(bayan);

        binding.addBayanToFavourites.setText("В отложенных");
    }
}