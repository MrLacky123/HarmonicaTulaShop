package com.example.harmonicatulashop.database.favourite.room;

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
import com.example.harmonicatulashop.database.favourite.dao.AccordionFavouriteDao;
import com.example.harmonicatulashop.database.favourite.dao.BayanFavouriteDao;
import com.example.harmonicatulashop.database.favourite.dao.HarmonicaFavouriteDao;
import com.example.harmonicatulashop.models.favourite.Accordion;
import com.example.harmonicatulashop.models.favourite.Bayan;
import com.example.harmonicatulashop.models.favourite.Harmonica;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Harmonica.class, Bayan.class, Accordion.class}, version = 1, exportSchema = false)
public abstract class FavouriteRoomDatabase extends RoomDatabase {

    public abstract HarmonicaFavouriteDao harmonicaDao();
    public abstract BayanFavouriteDao bayanDao();
    public abstract AccordionFavouriteDao accordionDao();

    private static volatile FavouriteRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static FavouriteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavouriteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FavouriteRoomDatabase.class, "favourite_database")
                            .addCallback(hRoomDatabaseCallback)
                            .addCallback(bRoomDatabaseCallback)
                            .addCallback(aRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Callback hRoomDatabaseCallback = new Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                HarmonicaFavouriteDao dao = INSTANCE.harmonicaDao();
                dao.deleteAll();
            });
        }
    };

    private static final Callback bRoomDatabaseCallback = new Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                BayanFavouriteDao dao = INSTANCE.bayanDao();
                dao.deleteAll();
            });
        }
    };

    private static final Callback aRoomDatabaseCallback = new Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AccordionFavouriteDao dao = INSTANCE.accordionDao();
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