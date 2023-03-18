package com.example.harmonicatulashop.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.databinding.FragmentFavouriteBinding;

public class FavouriteFragment extends Fragment {

    private FavouriteViewModel viewModel;

    private FragmentFavouriteBinding binding;

    public static FavouriteFragment newInstance() {
        return new FavouriteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(MainActivity.Instance).get(FavouriteViewModel.class);

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}