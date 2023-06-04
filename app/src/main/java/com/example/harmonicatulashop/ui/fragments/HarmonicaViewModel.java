package com.example.harmonicatulashop.ui.fragments;

import static com.example.harmonicatulashop.models.harmonica.Harmonica.ICON;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.ID;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.RANGE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.TONE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.TYPE;

import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentHarmonicaBinding;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.ui.account.fragments.AddHarmonicaFragment;
import com.example.harmonicatulashop.ui.catalog.fragments.HarmonicaCatalogFragment;

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

            MainActivity.INSTANCE.setFragment(HarmonicaCatalogFragment.class,
                    MainActivity.currentLayout, null,
                    MainActivity.INSTANCE.getResources().getString(R.string.title_harmonica),
                    null);

            return;
        }

        CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
        cartRepository.insertHarmonica(harmonica);

        binding.addHarmonicaToCart.setText("В корзине");
    }

    public void addToFavourites() {

        if (MainActivity.currentAdmin != null) {

            Bundle bundle = new Bundle();

            bundle.putInt(ID, harmonica.getId());
            bundle.putByteArray(ICON, harmonica.getIcon());
            bundle.putString(TYPE, harmonica.getType());
            bundle.putString(TONE, harmonica.getTone());
            bundle.putString(RANGE, harmonica.getRange());
            bundle.putInt(PRICE, harmonica.getPrice());
            bundle.putString(OPTIONS, harmonica.getOptions());

            MainActivity.INSTANCE.setFragment(AddHarmonicaFragment.class,
                    MainActivity.currentLayout, bundle,
                    "Редактировать гармонь",
                    HarmonicaFragment.class);

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