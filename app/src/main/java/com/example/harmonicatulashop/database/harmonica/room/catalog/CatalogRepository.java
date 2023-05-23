package com.example.harmonicatulashop.database.harmonica.room.catalog;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.harmonica.dao.AccordionDao;
import com.example.harmonicatulashop.database.harmonica.dao.BayanDao;
import com.example.harmonicatulashop.database.harmonica.dao.HarmonicaDao;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

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

    public void deleteHarmonica(Harmonica harmonica) {
        CatalogRoomDatabase.databaseWriteExecutor.execute(() -> harmonicaDao.delete(harmonica));
    }

    public LiveData<List<Bayan>> getAllBayans() {
        return allBayans;
    }

    public void insertBayan(Bayan bayan) {
        CatalogRoomDatabase.databaseWriteExecutor.execute(() -> bayanDao.insert(bayan));
    }

    public void deleteBayan(Bayan bayan) {
        CatalogRoomDatabase.databaseWriteExecutor.execute(() -> bayanDao.delete(bayan));
    }

    public LiveData<List<Accordion>> getAllAccordions() {
        return allAccordions;
    }

    public void insertAccordion(Accordion accordion) {
        CatalogRoomDatabase.databaseWriteExecutor.execute(() -> accordionDao.insert(accordion));
    }

    public void deleteAccordion(Accordion accordion) {
        CatalogRoomDatabase.databaseWriteExecutor.execute(() -> accordionDao.delete(accordion));
    }
}
