package com.example.harmonicatulashop.database.account.current.room;

import android.app.Application;

import com.example.harmonicatulashop.database.account.current.dao.AdminDaoC;
import com.example.harmonicatulashop.database.account.current.dao.UserDaoC;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.account.User;

public class CurrentRepository {

    private final AdminDaoC adminDaoC;
    private final UserDaoC userDaoC;

    public CurrentRepository(Application application) {
        CurrentRoomDatabase db = CurrentRoomDatabase.getDatabase(application);

        adminDaoC = db.adminDaoC();
        userDaoC = db.userDaoC();
    }

    public void setCurrentAdmin(Admin admin) {
        CurrentRoomDatabase.databaseWriteExecutor.execute(() -> {
            adminDaoC.delete();
            adminDaoC.setAdmin(admin);
        });
    }

    public void setCurrentUser(User user) {
        CurrentRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDaoC.delete();
            userDaoC.setUser(user);
        });
    }

    public void deleteCurrentAdmin() {
        CurrentRoomDatabase.databaseWriteExecutor.execute(adminDaoC::delete);
    }

    public void deleteCurrentUser() {
        CurrentRoomDatabase.databaseWriteExecutor.execute(userDaoC::delete);
    }
}
