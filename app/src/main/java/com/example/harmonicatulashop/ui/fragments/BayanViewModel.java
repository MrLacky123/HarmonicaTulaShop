package com.example.harmonicatulashop.ui.fragments;

import static com.example.harmonicatulashop.models.harmonica.Bayan.ICON;
import static com.example.harmonicatulashop.models.harmonica.Bayan.ID;
import static com.example.harmonicatulashop.models.harmonica.Bayan.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Bayan.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.RANGE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.TYPE;

import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentBayanBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.ui.account.fragments.AddBayanFragment;
import com.example.harmonicatulashop.ui.account.fragments.AddHarmonicaFragment;

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

            Bundle bundle = new Bundle();

            bundle.putInt(ID, bayan.getId());
            bundle.putByteArray(ICON, bayan.getIcon());
            bundle.putString(TYPE, bayan.getType());
            bundle.putString(RANGE, bayan.getRange());
            bundle.putInt(PRICE, bayan.getPrice());
            bundle.putString(OPTIONS, bayan.getOptions());

            MainActivity.INSTANCE.setFragment(AddBayanFragment.class,
                    MainActivity.currentLayout, bundle,
                    "Редактировать баян",
                    BayanFragment.class);

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