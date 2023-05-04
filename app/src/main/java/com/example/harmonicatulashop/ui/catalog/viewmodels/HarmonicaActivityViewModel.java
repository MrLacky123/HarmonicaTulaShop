package com.example.harmonicatulashop.ui.catalog.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.ActivityHarmonicaBinding;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.ui.catalog.activities.HarmonicaActivity;

public class HarmonicaActivityViewModel extends ViewModel {

    private ActivityHarmonicaBinding binding;
    private Harmonica harmonica;

    public void setBinding(ActivityHarmonicaBinding binding) {
        this.binding = binding;
    }

    public void setHarmonica(Harmonica harmonica) {
        this.harmonica = harmonica;
    }

    public void addToCart() {
        CartRepository cartRepository = new CartRepository(HarmonicaActivity.INSTANCE.getApplication());
        cartRepository.insertHarmonica(harmonica);

        binding.addHarmonicaToCart.setText("В корзине");

        HarmonicaActivity.INSTANCE.finish();
    }

    public void addToFavourites() {
        FavouriteRepository favouriteRepository = new FavouriteRepository(HarmonicaActivity.INSTANCE.getApplication());
        favouriteRepository.insertHarmonica(harmonica);

        binding.addHarmonicaToFavourites.setText("В отложенных");
    }

    public void close() {
        HarmonicaActivity.INSTANCE.finish();
    }
}
