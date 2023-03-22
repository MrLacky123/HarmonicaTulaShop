package com.example.harmonicatulashop.ui.catalog.db;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.harmonicatulashop.ui.catalog.db.dao.HarmonicaDao;
import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

import java.util.List;

public class HarmonicaRepository {

    private HarmonicaDao mHarmonicaDao;
    private LiveData<List<Harmonica>> mAllHarmonicas;

    public HarmonicaRepository(Application application) {
        HarmonicasRoomDatabase db = HarmonicasRoomDatabase.getDatabase(application);
        mHarmonicaDao = db.harmonicaDao();
        mAllHarmonicas = mHarmonicaDao.getHarmonicas();
    }

    public LiveData<List<Harmonica>> getAllWords() {
        return mAllHarmonicas;
    }

    public void insert(Harmonica harmonica) {
        HarmonicasRoomDatabase.databaseWriteExecutor.execute(() -> {
            mHarmonicaDao.insert(harmonica);
        });
    }
}
