package com.example.harmonicatulashop.database.harmonica.room.catalog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.harmonica.dao.AccordionDao;
import com.example.harmonicatulashop.database.harmonica.dao.BayanDao;
import com.example.harmonicatulashop.database.harmonica.dao.HarmonicaDao;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Harmonica.class, Bayan.class, Accordion.class}, version = 1, exportSchema = false)
public abstract class CatalogRoomDatabase extends RoomDatabase {

    public abstract HarmonicaDao harmonicaDao();
    public abstract AccordionDao accordionDao();
    public abstract BayanDao bayanDao();

    private static volatile CatalogRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CatalogRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CatalogRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CatalogRoomDatabase.class, "catalog_database")
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
                HarmonicaDao dao = INSTANCE.harmonicaDao();
                dao.deleteAll();
            });
        }
    };

    private static final RoomDatabase.Callback bRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                BayanDao dao = INSTANCE.bayanDao();
                dao.deleteAll();
            });
        }
    };

    private static final RoomDatabase.Callback aRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AccordionDao dao = INSTANCE.accordionDao();
                dao.deleteAll();
            });
        }
    };
}
