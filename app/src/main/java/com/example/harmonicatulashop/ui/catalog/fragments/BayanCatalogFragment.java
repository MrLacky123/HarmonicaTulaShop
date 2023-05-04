package com.example.harmonicatulashop.ui.catalog.fragments;

import static com.example.harmonicatulashop.ui.catalog.activities.BayanActivity.ICON;
import static com.example.harmonicatulashop.ui.catalog.activities.BayanActivity.ID;
import static com.example.harmonicatulashop.ui.catalog.activities.BayanActivity.OPTIONS;
import static com.example.harmonicatulashop.ui.catalog.activities.BayanActivity.PRICE;
import static com.example.harmonicatulashop.ui.catalog.activities.BayanActivity.RANGE;
import static com.example.harmonicatulashop.ui.catalog.activities.BayanActivity.TYPE;

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
import com.example.harmonicatulashop.databinding.FragmentBayanCatalogBinding;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;
import com.example.harmonicatulashop.ui.catalog.activities.BayanActivity;
import com.example.harmonicatulashop.ui.catalog.viewmodels.BayanCatalogViewModel;

public class BayanCatalogFragment extends Fragment {

    private BayanCatalogViewModel viewModel;
    private FragmentBayanCatalogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(BayanCatalogViewModel.class);

        binding = FragmentBayanCatalogBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        RecyclerView recyclerView = binding.bayanList;
        final BayanAdapter adapter = new BayanAdapter(new BayanAdapter.BayanDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllBayans().observe(MainActivity.INSTANCE, adapter::submitList);

        adapter.setOnItemClickListener(bayan -> {
            Intent intent = new Intent(MainActivity.INSTANCE, BayanActivity.class);

            intent.putExtra(ID, bayan.getId());
            intent.putExtra(ICON, bayan.getIcon());
            intent.putExtra(TYPE, bayan.getType());
            intent.putExtra(RANGE, bayan.getRange());
            intent.putExtra(PRICE, bayan.getPrice());
            intent.putExtra(OPTIONS, bayan.getOptions());

            startActivity(intent);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}