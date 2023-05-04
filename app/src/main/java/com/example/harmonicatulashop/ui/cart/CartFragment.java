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
import com.example.harmonicatulashop.databinding.FragmentCartBinding;
import com.example.harmonicatulashop.models.harmonica.adapters.AccordionAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.HarmonicaAdapter;

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

        final HarmonicaAdapter harmonicaAdapter = new HarmonicaAdapter(new HarmonicaAdapter.HarmonicaDiff());
        harmonicaCartList.setAdapter(harmonicaAdapter);
        harmonicaCartList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        cartViewModel.getAllHarmonicas().observe(MainActivity.INSTANCE, harmonicaAdapter::submitList);


        bayanCartList = binding.bayanCartList;

        final BayanAdapter bayanAdapter = new BayanAdapter(new BayanAdapter.BayanDiff());
        bayanCartList.setAdapter(bayanAdapter);
        bayanCartList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        cartViewModel.getAllBayans().observe(MainActivity.INSTANCE, bayanAdapter::submitList);


        accordionCartList = binding.accordionCartList;

        final AccordionAdapter accordionAdapter = new AccordionAdapter(new AccordionAdapter.AccordionDiff());
        accordionCartList.setAdapter(accordionAdapter);
        accordionCartList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        cartViewModel.getAllAccordions().observe(MainActivity.INSTANCE, accordionAdapter::submitList);


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}