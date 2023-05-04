package com.example.harmonicatulashop.ui.catalog.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRepository;
import com.example.harmonicatulashop.models.harmonica.Accordion;

import java.util.List;

public class AccordionCatalogViewModel extends AndroidViewModel {

    public LiveData<List<Accordion>> accordions;

    private CatalogRepository repository;

    public AccordionCatalogViewModel (Application application) {
        super(application);
        repository = new CatalogRepository(application);
        accordions = repository.getAllAccordions();
    }

    public LiveData<List<Accordion>> getAllAccordions() { return accordions; }

    public void insert(Accordion accordion) { repository.insertAccordion(accordion); }

}