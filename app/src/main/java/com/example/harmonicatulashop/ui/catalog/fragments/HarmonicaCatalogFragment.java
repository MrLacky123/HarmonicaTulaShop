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
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentHarmonicaCatalogBinding;
import com.example.harmonicatulashop.ui.catalog.db.adapters.HarmonicaAdapter;
import com.example.harmonicatulashop.ui.catalog.viewmodels.HarmonicaCatalogViewModel;

public class HarmonicaCatalogFragment extends Fragment {

    private FragmentHarmonicaCatalogBinding binding;

    private HarmonicaCatalogViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(HarmonicaCatalogViewModel.class);

        binding = FragmentHarmonicaCatalogBinding.inflate(inflater, container, false);
        binding.setViewmodel(viewModel);
        binding.executePendingBindings();

        RecyclerView recyclerView = binding.harmonicaList;
        final HarmonicaAdapter adapter = new HarmonicaAdapter(new HarmonicaAdapter.HarmonicaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.Instance));

        viewModel.getAllHarmonicas().observe(MainActivity.Instance, words -> {
            adapter.submitList(words);
        });

        return binding.getRoot();

    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}