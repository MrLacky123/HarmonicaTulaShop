package com.example.harmonicatulashop.database.account.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.ui.account.dao.AdminDao;
import com.example.harmonicatulashop.models.account.Admin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Admin.class}, version = 1, exportSchema = false)
public abstract class AdminRoomDatabase extends RoomDatabase {

    public abstract AdminDao adminDao();

    private static volatile AdminRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AdminRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (AdminRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AdminRoomDatabase.class, "admin_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

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
}
