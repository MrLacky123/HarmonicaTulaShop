package com.example.harmonicatulashop.ui.account.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.harmonicatulashop.database.order.room.OrderRepository;
import com.example.harmonicatulashop.models.order.Order;

import java.util.List;

public class OrderListViewModel extends AndroidViewModel {

    private LiveData<List<Order>> allOrders;

    private OrderRepository repository;

    public OrderListViewModel(Application application) {
        super(application);

        repository = new OrderRepository(application);
        allOrders = repository.getAllOrders();
    }

    public LiveData<List<Order>> getAllOrders() {
        return allOrders;
    }

    public LiveData<List<Order>> getOrdersOfOnePerson(int personId) {
        return repository.getOnePersonOrders(personId);
    }
}