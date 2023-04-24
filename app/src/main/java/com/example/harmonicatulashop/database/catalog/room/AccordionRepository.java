package com.example.harmonicatulashop.database.catalog.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.catalog.dao.AccordionDao;
import com.example.harmonicatulashop.models.harmonica.Accordion;

import java.util.List;

public class AccordionRepository {
    private AccordionDao accordionDao;

    private LiveData<List<Accordion>> allAccordions;

    public AccordionRepository(Application application) {
        AccordionRoomDatabase db = AccordionRoomDatabase.getDatabase(application);
        accordionDao = db.accordionDao();
        allAccordions = accordionDao.getAccordions();
    }

    public LiveData<List<Accordion>> getAllAccordions() {
        return allAccordions;
    }

    public void insert(Accordion accordion) {
        HarmonicasRoomDatabase.databaseWriteExecutor.execute(() -> {
            accordionDao.insert(accordion);
        });
    }
}
