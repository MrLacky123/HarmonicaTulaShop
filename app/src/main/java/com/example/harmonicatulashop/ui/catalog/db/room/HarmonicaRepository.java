package com.example.harmonicatulashop.ui.catalog.db.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.ui.catalog.db.dao.HarmonicaDao;
import com.example.harmonicatulashop.ui.models.Harmonica;

import java.util.List;

public class HarmonicaRepository {

    private HarmonicaDao harmonicaDao;
    private LiveData<List<Harmonica>> allHarmonicas;

    public HarmonicaRepository(Application application) {
        HarmonicasRoomDatabase db = HarmonicasRoomDatabase.getDatabase(application);
        harmonicaDao = db.harmonicaDao();
        allHarmonicas = harmonicaDao.getHarmonicas();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() {
        return allHarmonicas;
    }

    public void insert(Harmonica harmonica) {
        HarmonicasRoomDatabase.databaseWriteExecutor.execute(() -> {
            harmonicaDao.insert(harmonica);
        });
    }
}
