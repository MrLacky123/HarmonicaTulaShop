package com.example.harmonicatulashop.database.account.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.account.dao.AdminDao;
import com.example.harmonicatulashop.models.account.Admin;

import java.util.List;

public class AdminRepository {

    private AdminDao adminDao;

    private LiveData<List<Admin>> admins;

    public AdminRepository(Application application) {
        AdminRoomDatabase db = AdminRoomDatabase.getDatabase(application);
        adminDao = db.adminDao();
        admins = adminDao.getAdmins();
    }

    public void insert(Admin admin) {
        AdminRoomDatabase.databaseWriteExecutor.execute(() -> adminDao.insert(admin));
    }

    public void replaceAdmin(String login, Admin admin) {
        AdminRoomDatabase.databaseWriteExecutor.execute(() -> {
            adminDao.delete(adminDao.getAdminByLogin(login));
            adminDao.insert(admin);
        });
    }

    public LiveData<List<Admin>> getAdmins() {
        return admins;
    }
}
