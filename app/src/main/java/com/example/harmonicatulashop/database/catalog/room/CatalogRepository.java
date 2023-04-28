package com.example.harmonicatulashop.database.catalog.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.catalog.dao.AccordionDao;
import com.example.harmonicatulashop.database.catalog.dao.BayanDao;
import com.example.harmonicatulashop.database.catalog.dao.HarmonicaDao;
import com.example.harmonicatulashop.models.catalog.Accordion;
import com.example.harmonicatulashop.models.catalog.Bayan;
import com.example.harmonicatulashop.models.catalog.Harmonica;

import java.util.List;

public class CatalogRepository {

    private HarmonicaDao harmonicaDao;
    private BayanDao bayanDao;
    private AccordionDao accordionDao;

    private LiveData<List<Harmonica>> allHarmonicas;
    private LiveData<List<Bayan>> allBayans;
    private LiveData<List<Accordion>> allAccordions;

    public CatalogRepository(Application application) {
        CatalogRoomDatabase db = CatalogRoomDatabase.getDatabase(application);

        harmonicaDao = db.harmonicaDao();
        allHarmonicas = harmonicaDao.getHarmonicas();

        bayanDao = db.bayanDao();
        allBayans = bayanDao.getBayans();

        accordionDao = db.accordionDao();
        allAccordions = accordionDao.getAccordions();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() {
        return allHarmonicas;
    }

    public void insertHarmonica(Harmonica harmonica) {
        CatalogRoomDatabase.databaseWriteExecutor.execute(() -> harmonicaDao.insert(harmonica));
    }

    public LiveData<List<Bayan>> getAllBayans() {
        return allBayans;
    }

    public void insertBayan(Bayan bayan) {
        CatalogRoomDatabase.databaseWriteExecutor.execute(() -> bayanDao.insert(bayan));
    }

    public LiveData<List<Accordion>> getAllAccordions() {
        return allAccordions;
    }

    public void insertAccordion(Accordion accordion) {
        CatalogRoomDatabase.databaseWriteExecutor.execute(() -> accordionDao.insert(accordion));
    }
}
