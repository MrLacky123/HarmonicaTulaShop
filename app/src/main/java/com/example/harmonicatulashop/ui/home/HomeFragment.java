package com.example.harmonicatulashop.ui.home;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setViewModel(homeViewModel);
        binding.executePendingBindings();

        StringBuilder buf = new StringBuilder();

        new Thread(() -> {

            String str;

            InputStream is = this.getResources().openRawResource(R.raw.home);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            if (is!=null) {
                while (true) {
                    try {
                        if ((str = reader.readLine()) == null) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    buf.append(str).append("\n");
                }
            }

            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

        String outString = buf.toString();

        binding.textHome.setText(outString);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}