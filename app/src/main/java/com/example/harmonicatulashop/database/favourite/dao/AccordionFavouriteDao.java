package com.example.harmonicatulashop.database.favourite.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.favourite.Accordion;

import java.util.List;

@Dao
public interface AccordionFavouriteDao {
    @Insert
    void insert(Accordion accordion);

    @Query("DELETE FROM accordion_favourite_list")
    void deleteAll();

    @Delete
    void delete(Accordion accordion);

    @Query("SELECT * FROM accordion_favourite_list ORDER BY id")
    LiveData<List<Accordion>> getAccordions();
}
