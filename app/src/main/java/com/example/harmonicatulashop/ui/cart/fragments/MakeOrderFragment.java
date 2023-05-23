package com.example.harmonicatulashop.ui.cart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentMakeOrderBinding;
import com.example.harmonicatulashop.ui.cart.viewmodels.MakeOrderViewModel;

public class MakeOrderFragment extends Fragment {

    private MakeOrderViewModel viewModel;
    private FragmentMakeOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity.currentLayout = R.id.make_order_layout;

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(MakeOrderViewModel.class);

        binding = FragmentMakeOrderBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}