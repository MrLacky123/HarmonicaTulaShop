package com.example.harmonicatulashop.database.account.current.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.database.account.current.dao.AdminDaoC;
import com.example.harmonicatulashop.database.account.current.dao.UserDaoC;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.account.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Admin.class, User.class}, version = 1, exportSchema = false)
public abstract class CurrentRoomDatabase extends RoomDatabase {

    public abstract AdminDaoC adminDaoC();
    public abstract UserDaoC userDaoC();

    private static volatile CurrentRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CurrentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CurrentRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CurrentRoomDatabase.class, "current_database")
                            .addCallback(aRoomDatabaseCallback)
                            .addCallback(uRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback aRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AdminDaoC dao = INSTANCE.adminDaoC();
                dao.delete();
            });
        }
    };

    private static final RoomDatabase.Callback uRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                UserDaoC dao = INSTANCE.userDaoC();
                dao.delete();
            });
        }
    };
}
