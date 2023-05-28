package com.example.harmonicatulashop.database.account.current.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.account.Admin;

@Dao
public interface AdminDaoC {

    @Insert
    void setAdmin(Admin admin);

    @Query("DELETE FROM admin")
    void delete();

    @Query("SELECT * FROM admin")
    Admin getAdmin();
}
