package com.example.harmonicatulashop.ui.catalog.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.harmonicatulashop.ui.catalog.db.HarmonicasRoomDatabase;
import com.example.harmonicatulashop.ui.catalog.db.dao.HarmonicaDao;
import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

import java.util.List;

public class HarmonicaRepository {

    private HarmonicaDao harmonicaDao;
    private LiveData<List<Harmonica>> AllHarmonicas;

    public HarmonicaRepository(Application application) {
        HarmonicasRoomDatabase db = HarmonicasRoomDatabase.getDatabase(application);
        harmonicaDao = db.harmonicaDao();
        AllHarmonicas = harmonicaDao.getHarmonicas();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() {
        return AllHarmonicas;
    }

    public void insert(Harmonica harmonica) {
        HarmonicasRoomDatabase.databaseWriteExecutor.execute(() -> {
            harmonicaDao.insert(harmonica);
        });
    }
}
