package com.example.harmonicatulashop.database.account.current.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.account.User;

@Dao
public interface UserDaoC {

    @Insert
    void setUser(User user);

    @Query("DELETE FROM user")
    void delete();

    @Query("SELECT * FROM user")
    User getUser();

}
