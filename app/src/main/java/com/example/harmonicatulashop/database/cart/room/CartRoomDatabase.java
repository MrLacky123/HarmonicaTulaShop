package com.example.harmonicatulashop.database.cart.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.database.cart.dao.AccordionCartDao;
import com.example.harmonicatulashop.database.cart.dao.BayanCartDao;
import com.example.harmonicatulashop.database.cart.dao.HarmonicaCartDao;
import com.example.harmonicatulashop.models.cart.Accordion;
import com.example.harmonicatulashop.models.cart.Bayan;
import com.example.harmonicatulashop.models.cart.Harmonica;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Harmonica.class, Bayan.class, Accordion.class}, version = 1, exportSchema = false)
public abstract class CartRoomDatabase extends RoomDatabase {

    public abstract HarmonicaCartDao harmonicaDao();
    public abstract BayanCartDao bayanDao();
    public abstract AccordionCartDao accordionDao();

    private static volatile CartRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CartRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CartRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CartRoomDatabase.class, "cart_database")
                            .addCallback(hRoomDatabaseCallback)
                            .addCallback(bRoomDatabaseCallback)
                            .addCallback(aRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback hRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                HarmonicaCartDao dao = INSTANCE.harmonicaDao();
                dao.deleteAll();
            });
        }
    };

    private static final RoomDatabase.Callback bRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                BayanCartDao dao = INSTANCE.bayanDao();
                dao.deleteAll();
            });
        }
    };

    private static final RoomDatabase.Callback aRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AccordionCartDao dao = INSTANCE.accordionDao();
                dao.deleteAll();
            });
        }
    };
}