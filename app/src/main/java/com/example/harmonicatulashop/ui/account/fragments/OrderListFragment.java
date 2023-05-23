package com.example.harmonicatulashop.ui.account.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentOrderListBinding;
import com.example.harmonicatulashop.models.order.adapters.OrderAdapter;
import com.example.harmonicatulashop.ui.account.viewmodels.OrderListViewModel;

public class OrderListFragment extends Fragment {

    private OrderListViewModel viewModel;

    private FragmentOrderListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MainActivity.currentLayout = R.id.orders_layout;

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(OrderListViewModel.class);

        binding = FragmentOrderListBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        RecyclerView recyclerView = binding.orderList;
        final OrderAdapter adapter = new OrderAdapter(new OrderAdapter.OrderDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        if (MainActivity.currentUser != null) {
            viewModel.getOrdersOfOnePerson(MainActivity.currentUser.getId()).observe(MainActivity.INSTANCE, adapter::submitList);
        } else if (MainActivity.currentAdmin != null) {
            viewModel.getAllOrders().observe(MainActivity.INSTANCE, adapter::submitList);
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}