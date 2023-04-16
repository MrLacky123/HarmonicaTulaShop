package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.ui.catalog.db.room.AccordionRepository;
import com.example.harmonicatulashop.models.harmonica.Accordion;

import java.util.List;

public class AccordionCatalogViewModel extends AndroidViewModel {

    public LiveData<List<Accordion>> accordions;

    private AccordionRepository repository;

    public AccordionCatalogViewModel (Application application) {
        super(application);
        repository = new AccordionRepository(application);
        accordions = repository.getAllAccordions();
    }

    public LiveData<List<Accordion>> getAllAccordions() { return accordions; }

    public void insert(Accordion accordion) { repository.insert(accordion); }

}