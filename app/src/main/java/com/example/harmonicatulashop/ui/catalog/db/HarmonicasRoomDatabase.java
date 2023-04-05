package com.example.harmonicatulashop.ui.catalog.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.catalog.db.dao.HarmonicaDao;
import com.example.harmonicatulashop.ui.models.Harmonica;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Harmonica.class}, version = 1, exportSchema = false)
public abstract class HarmonicasRoomDatabase extends RoomDatabase {

    public abstract HarmonicaDao harmonicaDao();

    private static volatile HarmonicasRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static HarmonicasRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HarmonicasRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    HarmonicasRoomDatabase.class, "harmonica_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                HarmonicaDao dao = INSTANCE.harmonicaDao();
                dao.deleteAll();

                Harmonica harmonica = new Harmonica( String.valueOf(R.drawable.tulskaya301m),
                        "Тульская 301М", "Ля мажор", "25/25", 60000, "");
                dao.insert(harmonica);
                harmonica = new Harmonica(String.valueOf(R.drawable.kulikovopole_1__1_),
                        "Куликово поле", "До мажор", "27/25", 67000, "");
                dao.insert(harmonica);
            });
        }
    };
}
