package com.example.harmonicatulashop.ui.catalog.fragments;

import static com.example.harmonicatulashop.ui.catalog.activities.HarmonicaActivity.*;

import android.content.Intent;
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
import com.example.harmonicatulashop.databinding.FragmentHarmonicaCatalogBinding;
import com.example.harmonicatulashop.models.harmonica.adapters.HarmonicaAdapter;
import com.example.harmonicatulashop.ui.catalog.activities.HarmonicaActivity;
import com.example.harmonicatulashop.ui.catalog.viewmodels.HarmonicaCatalogViewModel;

public class HarmonicaCatalogFragment extends Fragment {

    public static final String HARMONICA = "HARMONICA";

    public static HarmonicaCatalogFragment INSTANCE;

    private FragmentHarmonicaCatalogBinding catalogBinding;

    private HarmonicaCatalogViewModel catalogViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        INSTANCE = this;

        catalogViewModel = new ViewModelProvider(MainActivity.INSTANCE).get(HarmonicaCatalogViewModel.class);

        catalogBinding = FragmentHarmonicaCatalogBinding.inflate(inflater, container, false);
        catalogBinding.setViewmodel(catalogViewModel);
        catalogBinding.executePendingBindings();

        RecyclerView recyclerView = catalogBinding.harmonicaList;
        final HarmonicaAdapter adapter = new HarmonicaAdapter(new HarmonicaAdapter.HarmonicaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        catalogViewModel.getAllHarmonicas().observe(MainActivity.INSTANCE, adapter::submitList);

        adapter.setOnItemClickListener(harmonica -> {

            Intent intent = new Intent(MainActivity.INSTANCE, HarmonicaActivity.class);

            intent.putExtra(ID, harmonica.getId());
            intent.putExtra(ICON, harmonica.getIcon());
            intent.putExtra(TYPE, harmonica.getType());
            intent.putExtra(TONE, harmonica.getTone());
            intent.putExtra(RANGE, harmonica.getRange());
            intent.putExtra(PRICE, harmonica.getPrice());
            intent.putExtra(OPTIONS, harmonica.getOptions());

            startActivity(intent);
        });

        return catalogBinding.getRoot();
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        catalogBinding = null;
    }
}