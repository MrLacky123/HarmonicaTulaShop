package com.example.harmonicatulashop.database.cart.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.cart.Accordion;

import java.util.List;

@Dao
public interface AccordionCartDao {
    @Insert
    void insert(Accordion accordion);

    @Query("DELETE FROM accordion_cart_list")
    void deleteAll();

    @Delete
    void delete(Accordion accordion);

    @Query("SELECT * FROM accordion_cart_list ORDER BY id")
    LiveData<List<Accordion>> getAccordions();
}
