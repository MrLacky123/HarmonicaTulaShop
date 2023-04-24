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
import com.example.harmonicatulashop.database.catalog.dao.BayanDao;
import com.example.harmonicatulashop.models.harmonica.Bayan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Bayan.class}, version = 1, exportSchema = false)
public abstract class BayanRoomDatabase extends RoomDatabase {

    public abstract BayanDao bayanDao();

    private static volatile BayanRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static BayanRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BayanRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BayanRoomDatabase.class, "bayan_list_database")
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
}
