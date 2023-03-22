package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.app.Application;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.ui.catalog.db.HarmonicaRepository;
import com.example.harmonicatulashop.ui.catalog.fragments.HarmonicaCatalogFragment;
import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

import java.util.List;

public class HarmonicaCatalogViewModel extends AndroidViewModel {

    public LiveData<List<Harmonica>> harmonicas = new MutableLiveData<List<Harmonica>>();

    private HarmonicaRepository mRepository;

    public HarmonicaCatalogViewModel (Application application) {
        super(application);
        mRepository = new HarmonicaRepository(application);
        harmonicas = mRepository.getAllWords();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() { return harmonicas; }

    public void insert(Harmonica harmonica) { mRepository.insert(harmonica); }
}