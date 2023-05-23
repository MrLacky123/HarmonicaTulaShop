package com.example.harmonicatulashop.database.order.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.harmonicatulashop.models.order.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insert(Order order);

    @Query("DELETE FROM orders")
    void deleteAll();

    @Delete
    void delete(Order order);

    @Query("SELECT * FROM orders ORDER BY id")
    LiveData<List<Order>> getAllOrders();

    @Query("SELECT * FROM orders WHERE personId LIKE :personId ORDER BY id")
    LiveData<List<Order>> getOnePersonOrders(int personId);

    @Query("SELECT * FROM orders WHERE id LIKE :id")
    Order findById(int id);
}
