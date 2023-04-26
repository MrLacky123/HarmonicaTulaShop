package com.example.harmonicatulashop.database.cart.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.database.cart.dao.HarmonicaCartDao;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Harmonica.class}, version = 1, exportSchema = false)
public abstract class HarmonicaCartRoomDatabase extends RoomDatabase {

    public abstract HarmonicaCartDao harmonicaDao();

    private static volatile HarmonicaCartRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static HarmonicaCartRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HarmonicaCartRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    HarmonicaCartRoomDatabase.class, "harmonica_cart_database")
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
                HarmonicaCartDao dao = INSTANCE.harmonicaDao();
                dao.deleteAll();
            });
        }
    };
}