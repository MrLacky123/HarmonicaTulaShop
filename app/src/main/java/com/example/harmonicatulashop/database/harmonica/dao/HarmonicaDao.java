package com.example.harmonicatulashop.database.harmonica.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.harmonica.Harmonica;

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

    @Query("SELECT * FROM harmonica_list WHERE id LIKE :id")
    Harmonica findById(int id);

    @Query("SELECT * FROM harmonica_list ORDER BY id")
    List<Harmonica> getHarmonicaList();
}
