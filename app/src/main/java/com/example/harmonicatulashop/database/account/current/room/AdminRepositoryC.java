package com.example.harmonicatulashop.database.account.current.room;

import android.app.Application;

import com.example.harmonicatulashop.database.account.current.dao.AdminDaoC;
import com.example.harmonicatulashop.models.account.Admin;

public class AdminRepositoryC {

    private final AdminDaoC adminDaoC;

    public AdminRepositoryC(Application application) {
        AdminRoomDatabaseC db = AdminRoomDatabaseC.getDatabase(application);

        adminDaoC = db.adminDaoC();
    }

    public void setCurrentAdmin(Admin admin) {
        AdminRoomDatabaseC.databaseWriteExecutor.execute(() -> {
            adminDaoC.delete();
            adminDaoC.setAdmin(admin);
        });
    }

    public void delete() {
        AdminRoomDatabaseC.databaseWriteExecutor.execute(adminDaoC::delete);
    }
}
