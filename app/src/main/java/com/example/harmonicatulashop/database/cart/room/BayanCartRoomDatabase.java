package com.example.harmonicatulashop.database.cart.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.database.cart.dao.BayanCartDao;
import com.example.harmonicatulashop.models.harmonica.Bayan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Bayan.class}, version = 1, exportSchema = false)
public abstract class BayanCartRoomDatabase extends RoomDatabase {

    public abstract BayanCartDao bayanDao();

    private static volatile BayanCartRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static BayanCartRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BayanCartRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BayanCartRoomDatabase.class, "bayan_cart_database")
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
                BayanCartDao dao = INSTANCE.bayanDao();
                dao.deleteAll();
            });
        }
    };
}