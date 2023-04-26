package com.example.harmonicatulashop.database.cart.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.cart.dao.BayanCartDao;
import com.example.harmonicatulashop.models.harmonica.Bayan;

import java.util.List;

public class BayanCartRepository {

    private BayanCartDao bayanDao;

    private LiveData<List<Bayan>> allBayans;

    public BayanCartRepository(Application application) {
        BayanCartRoomDatabase db = BayanCartRoomDatabase.getDatabase(application);
        bayanDao = db.bayanDao();
        allBayans = bayanDao.getBayans();
    }

    public LiveData<List<Bayan>> getAllBayans() {
        return allBayans;
    }

    public void insert(Bayan bayan) {
        BayanCartRoomDatabase.databaseWriteExecutor.execute(() -> {
            bayanDao.insert(bayan);
        });
    }
}
