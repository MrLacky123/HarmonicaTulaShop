package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.ActivityBayanBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.ui.catalog.activities.BayanActivity;

public class BayanActivityViewModel extends ViewModel {

    private ActivityBayanBinding binding;
    private Bayan bayan;

    public void setBinding(ActivityBayanBinding binding) {
        this.binding = binding;
    }

    public void setBayan(Bayan bayan) {
        this.bayan = bayan;
    }

    public void addToCart() {
        CartRepository cartRepository = new CartRepository(BayanActivity.INSTANCE.getApplication());
        cartRepository.insertBayan(bayan);

        binding.addBayanToCart.setText("В корзине");

        close();
    }

    public void addToFavourites() {
        FavouriteRepository favouriteRepository = new FavouriteRepository(BayanActivity.INSTANCE.getApplication());
        favouriteRepository.insertBayan(bayan);

        binding.addBayanToFavourites.setText("В отложенных");
    }

    public void close() {
        BayanActivity.INSTANCE.finish();
    }
}
