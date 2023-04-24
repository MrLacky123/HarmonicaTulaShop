package com.example.harmonicatulashop.ui.catalog.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.databinding.FragmentAccordionCatalogBinding;
import com.example.harmonicatulashop.database.catalog.adapters.AccordionAdapter;
import com.example.harmonicatulashop.ui.catalog.viewmodels.AccordionCatalogViewModel;

public class AccordionCatalogFragment extends Fragment {

    private AccordionCatalogViewModel viewModel;
    private FragmentAccordionCatalogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(MainActivity.Instance).get(AccordionCatalogViewModel.class);

        binding = FragmentAccordionCatalogBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        RecyclerView recyclerView = binding.accordionList;
        final AccordionAdapter adapter = new AccordionAdapter(new AccordionAdapter.AccordionDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.Instance));

        viewModel.getAllAccordions().observe(MainActivity.Instance, accordions -> {
            adapter.submitList(accordions);
        });

        return binding.getRoot();
    }
}