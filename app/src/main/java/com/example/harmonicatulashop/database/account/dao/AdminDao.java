package com.example.harmonicatulashop.database.account.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.account.Admin;

import java.util.List;

@Dao
public interface AdminDao {

    @Insert
    void insert(Admin admin);

    @Query("DELETE FROM admin")
    void deleteAll();

    @Delete
    void delete(Admin admin);

    @Query("SELECT * FROM admin ORDER BY id")
    LiveData<List<Admin>> getAdmins();

    @Query("SELECT * FROM admin WHERE login LIKE :login")
    Admin getAdminByLogin(String login);
}
