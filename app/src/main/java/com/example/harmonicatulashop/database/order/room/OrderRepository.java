package com.example.harmonicatulashop.database.order.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.harmonica.dao.AccordionDao;
import com.example.harmonicatulashop.database.harmonica.dao.BayanDao;
import com.example.harmonicatulashop.database.harmonica.dao.HarmonicaDao;
import com.example.harmonicatulashop.database.order.dao.OrderDao;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.order.Order;

import java.util.List;

public class OrderRepository {

    private OrderDao orderDao;
    private HarmonicaDao harmonicaDao;
    private BayanDao bayanDao;
    private AccordionDao accordionDao;
    private LiveData<List<Order>> allOrders;

    public OrderRepository(Application application) {
        OrderRoomDatabase db = OrderRoomDatabase.getDatabase(application);

        orderDao = db.orderDao();
        harmonicaDao = db.harmonicaDao();
        bayanDao = db.bayanDao();
        accordionDao = db.accordionDao();
        allOrders = orderDao.getAllOrders();
    }

    public LiveData<List<Order>> getAllOrders() {
        return allOrders;
    }

    public LiveData<List<Order>> getOnePersonOrders(int id) {
        return orderDao.getOnePersonOrders(id);
    }

    public void insertOrder(Order order) {
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> orderDao.insert(order));
    }

    public void insertHarmonica(Harmonica harmonica) {
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> {
            if (harmonicaDao.findById(harmonica.getId()) == null) {
                harmonicaDao.insert(harmonica);
            }
        });
    }

    public void insertBayan(Bayan bayan) {
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> {
            if (bayanDao.findById(bayan.getId()) == null) {
                bayanDao.insert(bayan);
            }
        });
    }

    public void insertAccordion(Accordion accordion) {
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> {
            if (accordionDao.findById(accordion.getId()) == null){
                accordionDao.insert(accordion);
            }
        });
    }
}
