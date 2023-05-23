package com.example.harmonicatulashop.database.account.current.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.database.account.current.dao.UserDaoC;
import com.example.harmonicatulashop.models.account.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserRoomDatabaseC extends RoomDatabase {

    public abstract UserDaoC userDaoC();

    private static volatile UserRoomDatabaseC INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserRoomDatabaseC getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabaseC.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    UserRoomDatabaseC.class, "current_user")
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
                UserDaoC dao = INSTANCE.userDaoC();
                dao.delete();
            });
        }
    };
}
