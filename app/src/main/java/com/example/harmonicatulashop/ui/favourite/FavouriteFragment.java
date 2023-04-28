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
import com.example.harmonicatulashop.database.cart.adapters.AccordionCartAdapter;
import com.example.harmonicatulashop.database.cart.adapters.BayanCartAdapter;
import com.example.harmonicatulashop.database.cart.adapters.HarmonicaCartAdapter;
import com.example.harmonicatulashop.database.favourite.adapters.AccordionFavouriteAdapter;
import com.example.harmonicatulashop.database.favourite.adapters.BayanFavouriteAdapter;
import com.example.harmonicatulashop.database.favourite.adapters.HarmonicaFavouriteAdapter;
import com.example.harmonicatulashop.databinding.FragmentFavouriteBinding;

public class FavouriteFragment extends Fragment {

    private FavouriteViewModel viewModel;

    private FragmentFavouriteBinding binding;

    public static RecyclerView harmonicaCartList;
    public static RecyclerView bayanCartList;
    public static RecyclerView accordionCartList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(MainActivity.Instance).get(FavouriteViewModel.class);

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.setBinding(binding);

        harmonicaCartList = binding.harmonicaFavouriteList;

        final HarmonicaFavouriteAdapter harmonicaAdapter = new HarmonicaFavouriteAdapter(new HarmonicaFavouriteAdapter.HarmonicaDiff());
        harmonicaCartList.setAdapter(harmonicaAdapter);
        harmonicaCartList.setLayoutManager(new LinearLayoutManager(MainActivity.Instance));

        viewModel.getAllHarmonicas().observe(MainActivity.Instance, harmonicaAdapter::submitList);


        bayanCartList = binding.bayanFavouriteList;

        final BayanFavouriteAdapter bayanAdapter = new BayanFavouriteAdapter(new BayanFavouriteAdapter.BayanDiff());
        bayanCartList.setAdapter(bayanAdapter);
        bayanCartList.setLayoutManager(new LinearLayoutManager(MainActivity.Instance));

        viewModel.getAllBayans().observe(MainActivity.Instance, bayanAdapter::submitList);


        accordionCartList = binding.bayanFavouriteList;

        final AccordionFavouriteAdapter accordionAdapter = new AccordionFavouriteAdapter(new AccordionFavouriteAdapter.AccordionDiff());
        accordionCartList.setAdapter(accordionAdapter);
        accordionCartList.setLayoutManager(new LinearLayoutManager(MainActivity.Instance));

        viewModel.getAllAccordions().observe(MainActivity.Instance, accordionAdapter::submitList);

        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}