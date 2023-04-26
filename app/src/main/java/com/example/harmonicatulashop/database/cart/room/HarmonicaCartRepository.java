package com.example.harmonicatulashop.database.cart.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.cart.dao.HarmonicaCartDao;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

import java.util.List;

public class HarmonicaCartRepository {

    private HarmonicaCartDao harmonicaDao;
    private LiveData<List<Harmonica>> allHarmonicas;

    public HarmonicaCartRepository(Application application) {
        HarmonicaCartRoomDatabase db = HarmonicaCartRoomDatabase.getDatabase(application);
        harmonicaDao = db.harmonicaDao();
        allHarmonicas = harmonicaDao.getHarmonicas();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() {
        return allHarmonicas;
    }

    public void insert(Harmonica harmonica) {
        HarmonicaCartRoomDatabase.databaseWriteExecutor.execute(() -> harmonicaDao.insert(harmonica));
    }
}