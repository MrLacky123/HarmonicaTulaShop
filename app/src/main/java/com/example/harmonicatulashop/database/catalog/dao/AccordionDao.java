package com.example.harmonicatulashop.database.catalog.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.catalog.Accordion;

import java.util.List;

@Dao
public interface AccordionDao {
    @Insert
    void insert(Accordion accordion);

    @Query("DELETE FROM accordion_catalog_list")
    void deleteAll();

    @Delete
    void delete(Accordion accordion);

    @Query("SELECT * FROM accordion_catalog_list ORDER BY id")
    LiveData<List<Accordion>> getAccordions();
}
