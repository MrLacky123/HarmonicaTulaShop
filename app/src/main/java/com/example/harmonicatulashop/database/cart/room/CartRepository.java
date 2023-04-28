package com.example.harmonicatulashop.database.cart.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.cart.dao.AccordionCartDao;
import com.example.harmonicatulashop.database.cart.dao.BayanCartDao;
import com.example.harmonicatulashop.database.cart.dao.HarmonicaCartDao;
import com.example.harmonicatulashop.models.cart.Accordion;
import com.example.harmonicatulashop.models.cart.Bayan;
import com.example.harmonicatulashop.models.cart.Harmonica;

import java.util.List;

public class CartRepository {

    private HarmonicaCartDao harmonicaDao;
    private BayanCartDao bayanDao;
    private AccordionCartDao accordionDao;
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
        CartRoomDatabase.databaseWriteExecutor.execute(() -> harmonicaDao.insert(harmonica));
    }

    public void insertBayan(Bayan bayan) {
        CartRoomDatabase.databaseWriteExecutor.execute(() -> bayanDao.insert(bayan));
    }

    public void insertAccordion(Accordion accordion) {
        CartRoomDatabase.databaseWriteExecutor.execute(() -> accordionDao.insert(accordion));
    }
}