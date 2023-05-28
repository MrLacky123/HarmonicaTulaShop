package com.example.harmonicatulashop.database.harmonica.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.harmonica.Accordion;

import java.util.List;

@Dao
public interface AccordionDao {
    @Insert
    void insert(Accordion accordion);

    @Query("DELETE FROM accordion_list")
    void deleteAll();

    @Delete
    void delete(Accordion accordion);

    @Query("SELECT * FROM accordion_list ORDER BY id")
    LiveData<List<Accordion>> getAccordions();

    @Query("SELECT * FROM accordion_list WHERE id LIKE :id")
    Accordion findById(int id);

    @Query("SELECT * FROM accordion_list ORDER BY id")
    List<Accordion> getAccordionList();
}
