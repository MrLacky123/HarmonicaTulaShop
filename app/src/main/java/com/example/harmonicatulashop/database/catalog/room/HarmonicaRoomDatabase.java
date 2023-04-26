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
import com.example.harmonicatulashop.database.catalog.dao.HarmonicaDao;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Harmonica.class}, version = 1, exportSchema = false)
public abstract class HarmonicaRoomDatabase extends RoomDatabase {

    public abstract HarmonicaDao harmonicaDao();

    private static volatile HarmonicaRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static HarmonicaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HarmonicaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    HarmonicaRoomDatabase.class, "harmonica_list_database")
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
}
