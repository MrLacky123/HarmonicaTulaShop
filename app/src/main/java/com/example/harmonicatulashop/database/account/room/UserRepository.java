package com.example.harmonicatulashop.database.account.room;

import android.app.Application;

import com.example.harmonicatulashop.database.account.dao.UserDao;
import com.example.harmonicatulashop.models.account.User;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    public void insert(User user) {
        AdminRoomDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }
}
