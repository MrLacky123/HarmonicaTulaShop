package com.example.harmonicatulashop.database.favourite.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.favourite.Bayan;

import java.util.List;

@Dao
public interface BayanFavouriteDao {

    @Insert
    void insert(Bayan bayan);

    @Query("DELETE FROM bayan_favourite_list")
    void deleteAll();

    @Delete
    void delete(Bayan bayan);

    @Query("SELECT * FROM bayan_favourite_list ORDER BY id")
    LiveData<List<Bayan>> getBayans();
}
