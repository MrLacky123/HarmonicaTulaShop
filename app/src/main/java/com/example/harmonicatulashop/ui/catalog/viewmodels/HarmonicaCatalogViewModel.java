package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.harmonicatulashop.ui.catalog.db.repository.HarmonicaRepository;
import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

import java.util.List;

public class HarmonicaCatalogViewModel extends AndroidViewModel {

    public LiveData<List<Harmonica>> harmonicas = new MutableLiveData<List<Harmonica>>();

    private HarmonicaRepository repository;

    public HarmonicaCatalogViewModel (Application application) {
        super(application);
        repository = new HarmonicaRepository(application);
        harmonicas = repository.getAllHarmonicas();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() { return harmonicas; }

    public void insert(Harmonica harmonica) { repository.insert(harmonica); }
}