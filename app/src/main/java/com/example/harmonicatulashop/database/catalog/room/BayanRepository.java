package com.example.harmonicatulashop.database.catalog.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.catalog.dao.BayanDao;
import com.example.harmonicatulashop.models.harmonica.Bayan;

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
        BayanRoomDatabase.databaseWriteExecutor.execute(() -> {
            bayanDao.insert(bayan);
        });
    }
}
