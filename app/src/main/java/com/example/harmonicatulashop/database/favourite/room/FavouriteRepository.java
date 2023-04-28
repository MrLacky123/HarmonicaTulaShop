package com.example.harmonicatulashop.database.favourite.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.favourite.dao.AccordionFavouriteDao;
import com.example.harmonicatulashop.database.favourite.dao.BayanFavouriteDao;
import com.example.harmonicatulashop.database.favourite.dao.HarmonicaFavouriteDao;
import com.example.harmonicatulashop.models.favourite.Accordion;
import com.example.harmonicatulashop.models.favourite.Bayan;
import com.example.harmonicatulashop.models.favourite.Harmonica;

import java.util.List;

public class FavouriteRepository {

    private HarmonicaFavouriteDao harmonicaDao;
    private BayanFavouriteDao bayanDao;
    private AccordionFavouriteDao accordionDao;
    private LiveData<List<Harmonica>> allHarmonicas;
    private LiveData<List<Bayan>> allBayans;
    private LiveData<List<Accordion>> allAccordions;

    public FavouriteRepository(Application application) {
        FavouriteRoomDatabase db = FavouriteRoomDatabase.getDatabase(application);

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
        FavouriteRoomDatabase.databaseWriteExecutor.execute(() -> harmonicaDao.insert(harmonica));
    }

    public void insertBayan(Bayan bayan) {
        FavouriteRoomDatabase.databaseWriteExecutor.execute(() -> bayanDao.insert(bayan));
    }

    public void insertAccordion(Accordion accordion) {
        FavouriteRoomDatabase.databaseWriteExecutor.execute(() -> accordionDao.insert(accordion));
    }
}