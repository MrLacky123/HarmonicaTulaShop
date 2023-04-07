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

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.catalog.db.dao.HarmonicaDao;
import com.example.harmonicatulashop.ui.models.Harmonica;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
                                    HarmonicasRoomDatabase.class, "harmonica_list_database")
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

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.Instance.getApplicationContext().getResources(), R.drawable.tulskaya301m);
                bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bos);
                byte[] blob = bos.toByteArray();

                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Harmonica harmonica = new Harmonica(blob, "Тульская 301М", "Ля мажор", "25/25", 60000, "");
                dao.insert(harmonica);

                bos = new ByteArrayOutputStream();

                bitmap = BitmapFactory.decodeResource(MainActivity.Instance.getApplicationContext().getResources(), R.drawable.kulikovopole_1__1_);
                bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                blob = bos.toByteArray();

                harmonica = new Harmonica(blob, "Куликово поле", "До мажор", "27/25", 67000, "");
                dao.insert(harmonica);
            });
        }
    };
}
