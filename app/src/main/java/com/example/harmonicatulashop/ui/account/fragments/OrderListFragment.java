package com.example.harmonicatulashop.ui.account.fragments;

import static com.example.harmonicatulashop.models.order.Order.ACCORDION_IDS;
import static com.example.harmonicatulashop.models.order.Order.BAYAN_IDS;
import static com.example.harmonicatulashop.models.order.Order.HARMONICA_IDS;
import static com.example.harmonicatulashop.models.order.Order.PERSON_ID;
import static com.example.harmonicatulashop.models.order.Order.PICKING_UP;
import static com.example.harmonicatulashop.models.order.Order.PRICE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentOrderListBinding;
import com.example.harmonicatulashop.models.order.Order;
import com.example.harmonicatulashop.models.order.adapters.OrderAdapter;
import com.example.harmonicatulashop.ui.account.viewmodels.OrderListViewModel;

public class OrderListFragment extends Fragment {

    private OrderListViewModel viewModel;

    private FragmentOrderListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

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

        adapter.setOnItemClickListener(order -> {

            String title = Order.NAME + "№" + order.getId();

            Bundle bundle = new Bundle();

            bundle.putByteArray(HARMONICA_IDS, order.getHarmonicaIds());
            bundle.putByteArray(BAYAN_IDS, order.getBayanIds());
            bundle.putByteArray(ACCORDION_IDS, order.getAccordionIds());
            bundle.putInt(PRICE, order.getPrice());
            bundle.putInt(PERSON_ID, order.getPersonId());
            bundle.putBoolean(PICKING_UP, order.isPickingUp());

            MainActivity.INSTANCE.setFragment(OrderFragment.class,
                    R.id.orders_layout, bundle,
                    title, OrderListFragment.class);

        });

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}