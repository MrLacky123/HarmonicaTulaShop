package com.example.harmonicatulashop.ui.cart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentMakeOrderBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.harmonica.adapters.AccordionAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.HarmonicaAdapter;
import com.example.harmonicatulashop.ui.cart.viewmodels.MakeOrderViewModel;
import com.example.harmonicatulashop.ui.fragments.AccordionFragment;
import com.example.harmonicatulashop.ui.fragments.BayanFragment;
import com.example.harmonicatulashop.ui.fragments.HarmonicaFragment;

import java.util.List;

public class MakeOrderFragment extends Fragment {

    private MakeOrderViewModel viewModel;
    private FragmentMakeOrderBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.currentLayout = R.id.make_order_layout;

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(MakeOrderViewModel.class);

        binding = FragmentMakeOrderBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.setBinding(binding);

        setHarmonicaListView();
        setBayanListView();
        setAccordionListView();

        if (MainActivity.currentUser.getAddress() != null) {
            if (!MainActivity.currentUser.getAddress().equals("")) {
                binding.inputDeliveryAddress.setText(MainActivity.currentUser.getAddress());
            }
        }

        return binding.getRoot();
    }

    public void setHarmonicaListView () {

        RecyclerView harmonicaMakeOrderList = binding.harmonicaMakeOrderList;

        final HarmonicaAdapter harmonicaAdapter = new HarmonicaAdapter(new HarmonicaAdapter.HarmonicaDiff());
        harmonicaMakeOrderList.setAdapter(harmonicaAdapter);
        harmonicaMakeOrderList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllHarmonicas().observe(MainActivity.INSTANCE, harmonicaAdapter::submitList);
        viewModel.getAllHarmonicas().observe(MainActivity.INSTANCE, harmonicaList -> {
            if (harmonicaList.isEmpty()) {
                binding.harmonicaMakeOrderCategory.setVisibility(View.GONE);
            }
        });
    }

    public void setBayanListView() {

        RecyclerView bayanMakeOrderList = binding.bayanMakeOrderList;

        final BayanAdapter bayanAdapter = new BayanAdapter(new BayanAdapter.BayanDiff());
        bayanMakeOrderList.setAdapter(bayanAdapter);
        bayanMakeOrderList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllBayans().observe(MainActivity.INSTANCE, bayanAdapter::submitList);
        viewModel.getAllBayans().observe(MainActivity.INSTANCE, bayanList -> {
            if (bayanList.isEmpty()) {
                binding.bayanMakeOrderCategory.setVisibility(View.GONE);
            }
        });
    }

    public void setAccordionListView() {

        RecyclerView accordionMakeOrderList = binding.accordionMakeOrderList;

        final AccordionAdapter accordionAdapter = new AccordionAdapter(new AccordionAdapter.AccordionDiff());
        accordionMakeOrderList.setAdapter(accordionAdapter);
        accordionMakeOrderList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllAccordions().observe(MainActivity.INSTANCE, accordionAdapter::submitList);
        viewModel.getAllAccordions().observe(MainActivity.INSTANCE, accordionList -> {
            if (accordionList.isEmpty()) {
                binding.accordionMakeOrderCategory.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}