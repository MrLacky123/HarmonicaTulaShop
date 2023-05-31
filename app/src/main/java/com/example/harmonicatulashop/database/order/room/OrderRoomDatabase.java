package com.example.harmonicatulashop.database.order.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.database.harmonica.dao.AccordionDao;
import com.example.harmonicatulashop.database.harmonica.dao.BayanDao;
import com.example.harmonicatulashop.database.harmonica.dao.HarmonicaDao;
import com.example.harmonicatulashop.database.order.dao.OrderDao;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.order.Order;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Order.class, Harmonica.class, Bayan.class, Accordion.class}, version = 2, exportSchema = false)
public abstract class OrderRoomDatabase extends RoomDatabase {

    public abstract OrderDao orderDao();
    public abstract HarmonicaDao harmonicaDao();
    public abstract BayanDao bayanDao();
    public abstract AccordionDao accordionDao();

    private static volatile OrderRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 16;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static OrderRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OrderRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OrderRoomDatabase.class, "order_database")
                            .addCallback(roomDatabaseCallback)
                            .addCallback(hRoomDatabaseCallback)
                            .addCallback(bRoomDatabaseCallback)
                            .addCallback(aRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                OrderDao dao = INSTANCE.orderDao();
                dao.deleteAll();
            });
        }
    };

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
