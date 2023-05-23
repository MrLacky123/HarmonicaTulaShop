package com.example.harmonicatulashop.ui.fragments;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRepository;
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

        if (MainActivity.currentAdmin != null) {

            new CatalogRepository(MainActivity.INSTANCE.getApplication()).deleteBayan(bayan);
            return;
        }

        CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
        cartRepository.insertBayan(bayan);

        binding.addBayanToCart.setText("В корзине");
    }

    public void addToFavourites() {

        if (MainActivity.currentAdmin != null) {

            return;
        } else if (MainActivity.currentUser == null) {

            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Войдите, чтобы добавить инструмент в отложенное", Toast.LENGTH_SHORT).show();
            return;
        }

        FavouriteRepository favouriteRepository = new FavouriteRepository(MainActivity.INSTANCE.getApplication());
        favouriteRepository.insertBayan(bayan);

        binding.addBayanToFavourites.setText("В отложенных");
    }
}