package com.example.harmonicatulashop.database.account.room;

import android.app.Application;

import com.example.harmonicatulashop.ui.account.dao.AdminDao;
import com.example.harmonicatulashop.models.account.Admin;

public class AdminRepository {

    private AdminDao adminDao;

    public AdminRepository(Application application) {
        AdminRoomDatabase db = AdminRoomDatabase.getDatabase(application);
        adminDao = db.adminDao();
    }

    public void insert(Admin admin) {
        AdminRoomDatabase.databaseWriteExecutor.execute(() -> adminDao.insert(admin));
    }
}
