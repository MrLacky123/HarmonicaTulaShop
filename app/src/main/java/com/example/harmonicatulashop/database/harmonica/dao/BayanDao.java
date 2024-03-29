package com.example.harmonicatulashop.database.harmonica.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.harmonica.Bayan;

import java.util.List;

@Dao
public interface BayanDao {

    @Insert
    void insert(Bayan bayan);

    @Query("DELETE FROM bayan_list")
    void deleteAll();

    @Delete
    void delete(Bayan bayan);

    @Query("SELECT * FROM bayan_list ORDER BY id")
    LiveData<List<Bayan>> getBayans();

    @Query("SELECT * FROM bayan_list WHERE id LIKE :id")
    Bayan findById(int id);

    @Query("SELECT * FROM bayan_list ORDER BY id")
    List<Bayan> getBayanList();
}
