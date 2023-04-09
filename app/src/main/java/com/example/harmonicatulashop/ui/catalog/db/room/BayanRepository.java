package com.example.harmonicatulashop.ui.catalog.db.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.ui.catalog.db.dao.BayanDao;
import com.example.harmonicatulashop.ui.models.Bayan;

import java.util.List;

public class BayanRepository {

    private BayanDao bayanDao;

    private LiveData<List<Bayan>> allBayans;

    public BayanRepository(Application application) {
        BayanRoomDatabase db = BayanRoomDatabase.getDatabase(application);
        bayanDao = db.bayanDao();
        allBayans = bayanDao.getBayans();
    }

    public LiveData<List<Bayan>> getAllBayans() {
        return allBayans;
    }

    public void insert(Bayan bayan) {
        HarmonicasRoomDatabase.databaseWriteExecutor.execute(() -> {
            bayanDao.insert(bayan);
        });
    }
}
