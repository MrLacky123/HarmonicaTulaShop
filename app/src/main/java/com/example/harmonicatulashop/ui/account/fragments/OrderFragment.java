package com.example.harmonicatulashop.ui.account.fragments;

import static com.example.harmonicatulashop.models.order.Order.ACCORDION_IDS;
import static com.example.harmonicatulashop.models.order.Order.BAYAN_IDS;
import static com.example.harmonicatulashop.models.order.Order.DELIVERY_ADDRESS;
import static com.example.harmonicatulashop.models.order.Order.HARMONICA_IDS;
import static com.example.harmonicatulashop.models.order.Order.ID;
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
import com.example.harmonicatulashop.databinding.FragmentOrderBinding;
import com.example.harmonicatulashop.models.harmonica.adapters.OrderAccordionAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.OrderBayanAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.OrderHarmonicaAdapter;
import com.example.harmonicatulashop.models.order.Order;
import com.example.harmonicatulashop.ui.account.viewmodels.OrderViewModel;

public class OrderFragment extends Fragment {

    private OrderViewModel viewModel;

    private FragmentOrderBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(OrderViewModel.class);

        binding = FragmentOrderBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        Bundle bundle = getArguments();

        assert bundle != null;
        Order order = new Order(bundle.getByteArray(HARMONICA_IDS),
                bundle.getByteArray(BAYAN_IDS),
                bundle.getByteArray(ACCORDION_IDS),
                bundle.getInt(PRICE),
                bundle.getInt(PERSON_ID),
                bundle.getBoolean(PICKING_UP),
                bundle.getString(DELIVERY_ADDRESS));

        order.setId(bundle.getInt(ID, 0));

        viewModel.setBinding(binding);
        viewModel.setOrder(order);

        RecyclerView harmonicaOrderList = binding.harmonicaOrderList;
        final OrderHarmonicaAdapter harmonicaAdapter = new OrderHarmonicaAdapter(viewModel.getHarmonicas());
        harmonicaOrderList.setAdapter(harmonicaAdapter);
        harmonicaOrderList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        RecyclerView bayanOrderList = binding.bayanOrderList;
        final OrderBayanAdapter bayanAdapter = new OrderBayanAdapter(viewModel.getBayans());
        bayanOrderList.setAdapter(bayanAdapter);
        bayanOrderList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        RecyclerView accordionOrderList = binding.accordionOrderList;
        final OrderAccordionAdapter accordionAdapter = new OrderAccordionAdapter(viewModel.getAccordions());
        accordionOrderList.setAdapter(accordionAdapter);
        accordionOrderList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        bindView(order);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.delete();
        binding = null;
    }

    public void bindView(Order order) {

        if (viewModel.getHarmonicas().isEmpty()) {
            binding.harmonicaOrderCategory.setVisibility(View.GONE);
        }

        if (viewModel.getBayans().isEmpty()) {
            binding.bayanOrderCategory.setVisibility(View.GONE);
        }

        if (viewModel.getAccordions().isEmpty()) {
            binding.accordionOrderCategory.setVisibility(View.GONE);
        }

        String price = " " + order.getPrice() + " ₽";

        binding.orderPrice.append(price);

        if (!(order.getDeliveryAddress() == null)) {
            binding.orderAddress.append(order.getDeliveryAddress());
        }

        if (order.isPickingUp()) {
            String string = ": Да";
            binding.isPickedUp.append(string);
        } else {
            String string = ": Нет";
            binding.isPickedUp.append(string);
        }
    }
}