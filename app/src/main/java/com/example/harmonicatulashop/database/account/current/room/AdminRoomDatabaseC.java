package com.example.harmonicatulashop.database.account.current.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.database.account.current.dao.AdminDaoC;
import com.example.harmonicatulashop.models.account.Admin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Admin.class}, version = 1, exportSchema = false)
public abstract class AdminRoomDatabaseC extends RoomDatabase {

    public abstract AdminDaoC adminDaoC();

    private static volatile AdminRoomDatabaseC INSTANCE;

    private static final int NUMBER_OF_THREADS = 1;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AdminRoomDatabaseC getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AdminRoomDatabaseC.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AdminRoomDatabaseC.class, "current_admin")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AdminDaoC dao = INSTANCE.adminDaoC();
                dao.delete();
            });
        }
    };
}
