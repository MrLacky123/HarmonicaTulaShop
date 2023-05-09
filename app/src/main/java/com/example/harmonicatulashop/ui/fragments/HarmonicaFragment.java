package com.example.harmonicatulashop.ui.fragments;

import static com.example.harmonicatulashop.models.harmonica.Harmonica.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.databinding.FragmentHarmonicaBinding;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

public class HarmonicaFragment extends Fragment {

    private HarmonicaViewModel viewModel;
    private FragmentHarmonicaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(HarmonicaViewModel.class);

        binding = FragmentHarmonicaBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        Bundle bundle = getArguments();

        assert bundle != null;
        Harmonica harmonica = new Harmonica(bundle.getByteArray(ICON),
                bundle.getString(TYPE),
                bundle.getString(TONE),
                bundle.getString(RANGE),
                bundle.getInt(PRICE, 0),
                bundle.getString(OPTIONS));

        harmonica.setId(bundle.getInt(ID, 0));

        viewModel.setHarmonica(harmonica);
        viewModel.setBinding(binding);

        bindView(harmonica);

        return binding.getRoot();
    }

    public void bindView(Harmonica harmonica){

        Bitmap bitmap = BitmapFactory.decodeByteArray(harmonica.getIcon(), 0, harmonica.getIcon().length);
        binding.harmonicaImage.setImageBitmap(bitmap);

        String price = harmonica.getPrice() + " руб.";
        binding.price.setText(price);

        binding.tone.append(" " + harmonica.getTone());
        binding.range.append(" " + harmonica.getRange());
        binding.harmonicaOptions.setText(harmonica.getOptions());
    }
}