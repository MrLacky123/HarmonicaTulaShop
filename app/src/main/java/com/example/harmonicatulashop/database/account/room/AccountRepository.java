package com.example.harmonicatulashop.database.account.room;

import android.app.Application;

import com.example.harmonicatulashop.database.account.dao.AdminDao;
import com.example.harmonicatulashop.database.account.dao.UserDao;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.account.User;

public class AccountRepository {

    private final AdminDao adminDao;
    private final UserDao userDao;

    public AccountRepository(Application application) {
        AccountRoomDatabase db = AccountRoomDatabase.getDatabase(application);

        adminDao = db.adminDao();
        userDao = db.userDao();
    }

    public void insertAdmin(Admin admin) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> adminDao.insert(admin));
    }

    public void replaceAdmin(String login, Admin admin) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
            adminDao.delete(adminDao.getAdminByLogin(login));
            adminDao.insert(admin);
        });
    }

    public void insertUser(User user) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    public void replaceUser(String login, User user) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.delete(userDao.getUserByLogin(login));
            userDao.insert(user);
        });
    }
}
