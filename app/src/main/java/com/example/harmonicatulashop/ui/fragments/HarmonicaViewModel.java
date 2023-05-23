package com.example.harmonicatulashop.ui.fragments;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRepository;
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

        if (MainActivity.currentAdmin != null) {

            new CatalogRepository(MainActivity.INSTANCE.getApplication()).deleteHarmonica(harmonica);
            return;
        }

        CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
        cartRepository.insertHarmonica(harmonica);

        binding.addHarmonicaToCart.setText("В корзине");
    }

    public void addToFavourites() {

        if (MainActivity.currentAdmin != null) {

            return;
        } else if (MainActivity.currentUser == null) {

            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Войдите, чтобы добавить инструмент в отложенное", Toast.LENGTH_SHORT).show();
            return;
        }

        FavouriteRepository favouriteRepository = new FavouriteRepository(MainActivity.INSTANCE.getApplication());
        favouriteRepository.insertHarmonica(harmonica);

        binding.addHarmonicaToFavourites.setText("В отложенных");
    }
}