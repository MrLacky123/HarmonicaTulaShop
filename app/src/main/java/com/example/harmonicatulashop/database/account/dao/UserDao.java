package com.example.harmonicatulashop.database.account.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.account.User;

import java.util.HashMap;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user ORDER BY login")
    LiveData<List<User>> getUsers();

    @Query("SELECT * FROM user WHERE login LIKE :login")
    User getUserByLogin(String login);
}
