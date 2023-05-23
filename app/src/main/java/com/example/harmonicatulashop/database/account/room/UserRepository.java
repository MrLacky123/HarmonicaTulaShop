package com.example.harmonicatulashop.database.account.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.account.current.room.UserRoomDatabaseC;
import com.example.harmonicatulashop.database.account.dao.UserDao;
import com.example.harmonicatulashop.models.account.User;

import java.util.List;

public class UserRepository {

    private UserDao userDao;

    private LiveData<List<User>> users;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        users = userDao.getUsers();
    }

    public void insert(User user) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    public void replaceUser(String login, User user) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.delete(userDao.getUserByLogin(login));
            userDao.insert(user);
        });
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }
}
