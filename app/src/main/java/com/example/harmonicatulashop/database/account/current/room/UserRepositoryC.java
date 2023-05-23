package com.example.harmonicatulashop.database.account.current.room;

import android.app.Application;

import com.example.harmonicatulashop.database.account.current.dao.UserDaoC;
import com.example.harmonicatulashop.models.account.User;

public class UserRepositoryC {

    private UserDaoC userDaoC;

    public UserRepositoryC(Application application) {
        UserRoomDatabaseC db = UserRoomDatabaseC.getDatabase(application);

        userDaoC = db.userDaoC();
    }

    public void setCurrentUser(User user) {
        UserRoomDatabaseC.databaseWriteExecutor.execute(() -> {
            userDaoC.delete();
            userDaoC.setUser(user);
        });
    }

    public void delete() {
        UserRoomDatabaseC.databaseWriteExecutor.execute(() -> userDaoC.delete());
    }
}
