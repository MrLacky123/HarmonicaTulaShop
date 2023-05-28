package com.example.harmonicatulashop.database.account.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.database.account.dao.AdminDao;
import com.example.harmonicatulashop.database.account.dao.UserDao;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.account.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Admin.class, User.class}, version = 1, exportSchema = false)
public abstract class AccountRoomDatabase extends RoomDatabase {

    public abstract AdminDao adminDao();
    public abstract UserDao userDao();

    private static volatile AccountRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AccountRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (AccountRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AccountRoomDatabase.class, "account_database")
                            .addCallback(aRoomDatabaseCallback)
                            .addCallback(uRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback aRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AdminDao dao = INSTANCE.adminDao();
                dao.deleteAll();

                String password = "changeYourPassword";
                Admin admin = new Admin(null, "firstadmin", password.hashCode(), "Admin1", null);
                dao.insert(admin);
            });
        }
    };

    private static final RoomDatabase.Callback uRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                UserDao dao = INSTANCE.userDao();
                dao.deleteAll();
            });
        }
    };
}
