package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.graphics.drawable.Icon;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

public class HarmonicaViewModel extends ViewModel {

    public MutableLiveData<Harmonica> harmonica = new MutableLiveData<Harmonica>();

    public HarmonicaViewModel() {
        harmonica.setValue(new Harmonica());
    }

    public void addToCart() {

        Button button = MainActivity.Instance.findViewById(R.id.add_harmonica_to_cart);
        button.setText("В корзине");

    }

    public void addToFavourites() {

        ImageButton imageButton = MainActivity.Instance.findViewById(R.id.add_harmonica_to_favourites);
        imageButton.setImageIcon(Icon.createWithFilePath("D:\\HarmonicaTulaShop\\app\\src\\main\\res\\drawable\\baseline_favorite_24.xml"));

    }
}
