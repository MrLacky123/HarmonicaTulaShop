package com.example.harmonicatulashop.database.order.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.order.dao.OrderDao;
import com.example.harmonicatulashop.models.order.Order;

import java.util.List;

public class OrderRepository {

    private OrderDao orderDao;
    private LiveData<List<Order>> allOrders;

    public OrderRepository(Application application) {
        OrderRoomDatabase db = OrderRoomDatabase.getDatabase(application);

        orderDao = db.orderDao();
        allOrders = orderDao.getAllOrders();
    }

    public LiveData<List<Order>> getAllOrders() {
        return allOrders;
    }

    public LiveData<List<Order>> getOnePersonOrders(int id) {
        return orderDao.getOnePersonOrders(id);
    }

    public void insert(Order order) {
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> orderDao.insert(order));
    }

    public void deleteOrder(Order order) {
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> orderDao.delete(order));
    }
}
