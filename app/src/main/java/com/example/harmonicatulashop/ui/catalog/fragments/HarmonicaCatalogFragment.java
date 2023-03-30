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
import com.example.harmonicatulashop.databinding.ItemHarmonicaBinding;
import com.example.harmonicatulashop.ui.catalog.db.adapters.HarmonicaAdapter;
import com.example.harmonicatulashop.ui.catalog.viewmodels.HarmonicaCatalogViewModel;
import com.example.harmonicatulashop.ui.catalog.viewmodels.HarmonicaViewModel;

public class HarmonicaCatalogFragment extends Fragment {

    private FragmentHarmonicaCatalogBinding catalogBinding;

    private HarmonicaCatalogViewModel catalogViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        catalogViewModel = new ViewModelProvider(this).get(HarmonicaCatalogViewModel.class);

        catalogBinding = FragmentHarmonicaCatalogBinding.inflate(inflater, container, false);
        catalogBinding.setViewmodel(catalogViewModel);
        catalogBinding.executePendingBindings();

        RecyclerView recyclerView = catalogBinding.harmonicaList;
        final HarmonicaAdapter adapter = new HarmonicaAdapter(new HarmonicaAdapter.HarmonicaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.Instance));

        catalogViewModel.getAllHarmonicas().observe(MainActivity.Instance, words -> {
            adapter.submitList(words);
        });

        return catalogBinding.getRoot();

    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        catalogBinding = null;
    }
}