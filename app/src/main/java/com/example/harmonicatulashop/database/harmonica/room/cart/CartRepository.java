package com.example.harmonicatulashop.database.harmonica.room.cart;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.harmonica.dao.AccordionDao;
import com.example.harmonicatulashop.database.harmonica.dao.BayanDao;
import com.example.harmonicatulashop.database.harmonica.dao.HarmonicaDao;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRoomDatabase;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

import java.util.List;

public class CartRepository {

    private HarmonicaDao harmonicaDao;
    private BayanDao bayanDao;
    private AccordionDao accordionDao;
    private LiveData<List<Harmonica>> allHarmonicas;
    private LiveData<List<Bayan>> allBayans;
    private LiveData<List<Accordion>> allAccordions;

    public CartRepository(Application application) {
        CartRoomDatabase db = CartRoomDatabase.getDatabase(application);

        harmonicaDao = db.harmonicaDao();
        bayanDao = db.bayanDao();
        accordionDao = db.accordionDao();

        allHarmonicas = harmonicaDao.getHarmonicas();
        allBayans = bayanDao.getBayans();
        allAccordions = accordionDao.getAccordions();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() {
        return allHarmonicas;
    }
    public LiveData<List<Bayan>> getAllBayans() {
        return allBayans;
    }
    public LiveData<List<Accordion>> getAllAccordions() {
        return allAccordions;
    }

    public void insertHarmonica(Harmonica harmonica) {
        CartRoomDatabase.databaseWriteExecutor.execute(() -> {
            if (harmonicaDao.findById(harmonica.getId()) == null) {
                harmonicaDao.insert(harmonica);
            }
        });
    }

    public void insertBayan(Bayan bayan) {
        CartRoomDatabase.databaseWriteExecutor.execute(() -> {
            if (bayanDao.findById(bayan.getId()) == null) {
                bayanDao.insert(bayan);
            }
        });
    }

    public void insertAccordion(Accordion accordion) {
        CartRoomDatabase.databaseWriteExecutor.execute(() -> {
            if (accordionDao.findById(accordion.getId()) == null){
                accordionDao.insert(accordion);
            }
        });
    }
}