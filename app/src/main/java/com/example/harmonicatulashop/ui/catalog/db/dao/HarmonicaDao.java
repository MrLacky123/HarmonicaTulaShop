package com.example.harmonicatulashop.ui.catalog.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.ui.models.Harmonica;

import java.util.List;

@Dao
public interface HarmonicaDao {

    @Insert
    void insert(Harmonica harmonica);

    @Query("DELETE FROM harmonica_list")
    void deleteAll();

    @Delete
    void delete(Harmonica harmonica);

    @Query("SELECT * FROM harmonica_list ORDER BY id")
    LiveData<List<Harmonica>> getHarmonicas();

}
