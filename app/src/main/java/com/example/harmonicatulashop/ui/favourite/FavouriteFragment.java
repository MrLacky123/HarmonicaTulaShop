package com.example.harmonicatulashop.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.databinding.FragmentFavouriteBinding;
import com.example.harmonicatulashop.models.harmonica.adapters.AccordionAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.HarmonicaAdapter;

public class FavouriteFragment extends Fragment {

    private FavouriteViewModel viewModel;

    private FragmentFavouriteBinding binding;

    public static RecyclerView harmonicaCartList;
    public static RecyclerView bayanCartList;
    public static RecyclerView accordionCartList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(FavouriteViewModel.class);

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.setBinding(binding);

        harmonicaCartList = binding.harmonicaFavouriteList;

        final HarmonicaAdapter harmonicaAdapter = new HarmonicaAdapter(new HarmonicaAdapter.HarmonicaDiff());
        harmonicaCartList.setAdapter(harmonicaAdapter);
        harmonicaCartList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllHarmonicas().observe(MainActivity.INSTANCE, harmonicaAdapter::submitList);


        bayanCartList = binding.bayanFavouriteList;

        final BayanAdapter bayanAdapter = new BayanAdapter(new BayanAdapter.BayanDiff());
        bayanCartList.setAdapter(bayanAdapter);
        bayanCartList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllBayans().observe(MainActivity.INSTANCE, bayanAdapter::submitList);


        accordionCartList = binding.accordionFavouriteList;

        final AccordionAdapter accordionAdapter = new AccordionAdapter(new AccordionAdapter.AccordionDiff());
        accordionCartList.setAdapter(accordionAdapter);
        accordionCartList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllAccordions().observe(MainActivity.INSTANCE, accordionAdapter::submitList);

        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}