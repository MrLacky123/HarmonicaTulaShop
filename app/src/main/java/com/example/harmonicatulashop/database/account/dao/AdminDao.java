package com.example.harmonicatulashop.database.account.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.account.Admin;

@Dao
public interface AdminDao {

    @Insert
    void insert(Admin admin);

    @Query("DELETE FROM admin")
    void deleteAll();

    @Delete
    void delete(Admin admin);
}
