package com.example.harmonicatulashop.database.cart.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.cart.dao.AccordionCartDao;
import com.example.harmonicatulashop.models.harmonica.Accordion;

import java.util.List;

public class AccordionCartRepository {
    private AccordionCartDao accordionDao;

    private LiveData<List<Accordion>> allAccordions;

    public AccordionCartRepository(Application application) {
        AccordionCartRoomDatabase db = AccordionCartRoomDatabase.getDatabase(application);
        accordionDao = db.accordionDao();
        allAccordions = accordionDao.getAccordions();
    }

    public LiveData<List<Accordion>> getAllAccordions() {
        return allAccordions;
    }

    public void insert(Accordion accordion) {
        AccordionCartRoomDatabase.databaseWriteExecutor.execute(() -> {
            accordionDao.insert(accordion);
        });
    }
}
