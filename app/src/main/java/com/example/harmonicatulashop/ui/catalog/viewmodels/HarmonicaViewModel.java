package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.app.Application;
import android.graphics.drawable.Icon;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.harmonicatulashop.databinding.ItemHarmonicaBinding;
import com.example.harmonicatulashop.ui.catalog.db.HarmonicaRepository;
import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

import java.util.List;

public class HarmonicaViewModel extends AndroidViewModel {

    public MutableLiveData<List<Harmonica>> harmonicas = new MutableLiveData<List<Harmonica>>();

    private ItemHarmonicaBinding binding;

    private HarmonicaRepository mRepository;

    public HarmonicaViewModel (Application application) {
        super(application);
        mRepository = new HarmonicaRepository(application);
        harmonicas = mRepository.getAllWords();
    }

    MutableLiveData<List<Harmonica>> getAllHarmonicas() { return harmonicas; }

    public void insert(Harmonica harmonica) { mRepository.insert(harmonica); }

    public void addToCart() {

        binding.addToCart.setText("В корзине");

    }

    public void addToFavourites() {

        binding.addToFavourites.setImageIcon(Icon.createWithFilePath("D:\\HarmonicaTulaShop\\app\\src\\main\\res\\drawable\\baseline_favorite_24.xml"));

    }
}
