package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.ui.catalog.db.room.HarmonicaRepository;
import com.example.harmonicatulashop.ui.models.Harmonica;

import java.util.List;

public class HarmonicaCatalogViewModel extends AndroidViewModel {

    public LiveData<List<Harmonica>> harmonicas;

    private HarmonicaRepository repository;

    public HarmonicaCatalogViewModel (Application application) {
        super(application);
        repository = new HarmonicaRepository(application);
        harmonicas = repository.getAllHarmonicas();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() { return harmonicas; }

    public void insert(Harmonica harmonica) { repository.insert(harmonica); }
}