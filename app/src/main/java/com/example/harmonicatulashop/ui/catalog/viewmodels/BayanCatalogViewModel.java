package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.ui.catalog.db.room.BayanRepository;
import com.example.harmonicatulashop.models.harmonica.Bayan;

import java.util.List;

public class BayanCatalogViewModel extends AndroidViewModel {

    public LiveData<List<Bayan>> bayans;

    private BayanRepository repository;

    public BayanCatalogViewModel (Application application) {
        super(application);
        repository = new BayanRepository(application);
        bayans = repository.getAllBayans();
    }

    public LiveData<List<Bayan>> getAllBayans() { return bayans; }

    public void insert(Bayan bayan) { repository.insert(bayan); }

}