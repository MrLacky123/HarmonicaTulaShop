package com.example.harmonicatulashop.database.cart.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.cart.Bayan;

import java.util.List;

@Dao
public interface BayanCartDao {

    @Insert
    void insert(Bayan bayan);

    @Query("DELETE FROM bayan_cart_list")
    void deleteAll();

    @Delete
    void delete(Bayan bayan);

    @Query("SELECT * FROM bayan_cart_list ORDER BY id")
    LiveData<List<Bayan>> getBayans();
}
