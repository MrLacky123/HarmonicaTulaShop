package com.example.harmonicatulashop.database.account.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.account.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Delete
    void delete(User user);
}
