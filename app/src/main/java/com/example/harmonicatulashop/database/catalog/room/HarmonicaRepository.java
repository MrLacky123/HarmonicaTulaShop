package com.example.harmonicatulashop.database.catalog.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.catalog.dao.HarmonicaDao;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

import java.util.List;

public class HarmonicaRepository {

    private HarmonicaDao harmonicaDao;
    private LiveData<List<Harmonica>> allHarmonicas;

    public HarmonicaRepository(Application application) {
        HarmonicaRoomDatabase db = HarmonicaRoomDatabase.getDatabase(application);
        harmonicaDao = db.harmonicaDao();
        allHarmonicas = harmonicaDao.getHarmonicas();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() {
        return allHarmonicas;
    }

    public void insert(Harmonica harmonica) {
        HarmonicaRoomDatabase.databaseWriteExecutor.execute(() -> harmonicaDao.insert(harmonica));
    }
}
