package com.example.harmonicatulashop.database.catalog.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.catalog.Bayan;

import java.util.List;

@Dao
public interface BayanDao {

    @Insert
    void insert(Bayan bayan);

    @Query("DELETE FROM bayan_catalog_list")
    void deleteAll();

    @Delete
    void delete(Bayan bayan);

    @Query("SELECT * FROM bayan_catalog_list ORDER BY id")
    LiveData<List<Bayan>> getBayans();
}