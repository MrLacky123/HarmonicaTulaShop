package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.catalog.room.CatalogRepository;
import com.example.harmonicatulashop.models.catalog.Harmonica;

import java.util.List;

public class HarmonicaCatalogViewModel extends AndroidViewModel {

    public LiveData<List<Harmonica>> harmonicas;

    private CatalogRepository repository;

    public HarmonicaCatalogViewModel (Application application) {
        super(application);
        repository = new CatalogRepository(application);
        harmonicas = repository.getAllHarmonicas();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() { return harmonicas; }

    public void insert(Harmonica harmonica) { repository.insertHarmonica(harmonica); }
}