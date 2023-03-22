package com.example.harmonicatulashop.ui.catalog.db.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

import java.util.List;

@Dao
public interface HarmonicaDao {

    @Insert
    void insert(Harmonica harmonica);

    @Query("DELETE FROM harmonica")
    void deleteAll();

    @Delete
    void delete(Harmonica harmonica);

    @Query("SELECT * FROM harmonica ORDER BY id")
    LiveData<List<Harmonica>> getHarmonicas();

}
