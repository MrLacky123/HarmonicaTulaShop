package com.example.harmonicatulashop.ui.favourite;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentFavouriteBinding;
import com.example.harmonicatulashop.databinding.FragmentHomeBinding;

public class FavouriteFragment extends Fragment {

    private FragmentFavouriteBinding binding;

    public static FavouriteFragment newInstance() {
        return new FavouriteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FavouriteViewModel favouriteViewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFavourite;
        favouriteViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}