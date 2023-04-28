package com.example.harmonicatulashop.database.catalog.room;

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
import com.example.harmonicatulashop.database.catalog.dao.AccordionDao;
import com.example.harmonicatulashop.database.catalog.dao.BayanDao;
import com.example.harmonicatulashop.database.catalog.dao.HarmonicaDao;
import com.example.harmonicatulashop.models.catalog.Accordion;
import com.example.harmonicatulashop.models.catalog.Bayan;
import com.example.harmonicatulashop.models.catalog.Harmonica;

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

    static CatalogRoomDatabase getDatabase(final Context context) {
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

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.Instance.getApplicationContext().getResources(), R.drawable.kulikovopole);
                bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] blob = bos.toByteArray();

                Harmonica harmonica = new Harmonica(blob, "Куликово поле", "До мажор", "27/25", 67000, "Регулировка ремня металлическим колёсиком");
                dao.insert(harmonica);
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

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.Instance.getApplicationContext().getResources(), R.drawable.bayan);
                bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bos);
                byte[] blob = bos.toByteArray();

                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Bayan bayan = new Bayan(blob, "Этюд", "80/100", 75000, "");
                dao.insert(bayan);
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

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.Instance.getApplicationContext().getResources(), R.drawable.accordion);
                bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bos);
                byte[] blob = bos.toByteArray();

                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Accordion accordion = new Accordion(blob, "55/100", 115000, "пять регистров");
                dao.insert(accordion);
            });
        }
    };
}
