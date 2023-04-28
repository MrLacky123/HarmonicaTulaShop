package com.example.harmonicatulashop.ui.cart;

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
import com.example.harmonicatulashop.database.cart.adapters.AccordionCartAdapter;
import com.example.harmonicatulashop.database.cart.adapters.BayanCartAdapter;
import com.example.harmonicatulashop.database.cart.adapters.HarmonicaCartAdapter;
import com.example.harmonicatulashop.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    public static RecyclerView harmonicaCartList;
    public static RecyclerView bayanCartList;
    public static RecyclerView accordionCartList;

    private CartViewModel cartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        binding.setViewModel(cartViewModel);
        binding.executePendingBindings();

        cartViewModel.setBinding(binding);


        harmonicaCartList = binding.harmonicaCartList;

        final HarmonicaCartAdapter harmonicaAdapter = new HarmonicaCartAdapter(new HarmonicaCartAdapter.HarmonicaDiff());
        harmonicaCartList.setAdapter(harmonicaAdapter);
        harmonicaCartList.setLayoutManager(new LinearLayoutManager(MainActivity.Instance));

        cartViewModel.getAllHarmonicas().observe(MainActivity.Instance, harmonicaAdapter::submitList);


        bayanCartList = binding.bayanCartList;

        final BayanCartAdapter bayanAdapter = new BayanCartAdapter(new BayanCartAdapter.BayanDiff());
        bayanCartList.setAdapter(bayanAdapter);
        bayanCartList.setLayoutManager(new LinearLayoutManager(MainActivity.Instance));

        cartViewModel.getAllBayans().observe(MainActivity.Instance, bayanAdapter::submitList);


        accordionCartList = binding.accordionCartList;

        final AccordionCartAdapter accordionAdapter = new AccordionCartAdapter(new AccordionCartAdapter.AccordionDiff());
        accordionCartList.setAdapter(accordionAdapter);
        accordionCartList.setLayoutManager(new LinearLayoutManager(MainActivity.Instance));

        cartViewModel.getAllAccordions().observe(MainActivity.Instance, accordionAdapter::submitList);


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}