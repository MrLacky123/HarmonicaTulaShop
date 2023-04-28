package com.example.harmonicatulashop.database.cart.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.cart.Harmonica;

import java.util.List;

@Dao
public interface HarmonicaCartDao {

    @Insert
    void insert(Harmonica harmonica);

    @Query("DELETE FROM harmonica_cart_list")
    void deleteAll();

    @Delete
    void delete(Harmonica harmonica);

    @Query("SELECT * FROM harmonica_cart_list ORDER BY id")
    LiveData<List<Harmonica>> getHarmonicas();

}