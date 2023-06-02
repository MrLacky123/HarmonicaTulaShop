package com.example.harmonicatulashop.ui.account.fragments;

import static com.example.harmonicatulashop.models.harmonica.Harmonica.ICON;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.ID;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.RANGE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.TONE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.TYPE;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentAddHarmonicaBinding;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.ui.account.viewmodels.AddHarmonicaViewModel;

public class AddHarmonicaFragment extends Fragment {

    private AddHarmonicaViewModel viewModel;

    private FragmentAddHarmonicaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(AddHarmonicaViewModel.class);

        binding = FragmentAddHarmonicaBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        Bundle bundle = getArguments();
        bindView(bundle);

        viewModel.setBinding(binding);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void bindView(Bundle bundle) {

        if (bundle == null){
            return;
        }

        Harmonica harmonica = new Harmonica(bundle.getByteArray(ICON),
                bundle.getString(TYPE),
                bundle.getString(TONE),
                bundle.getString(RANGE),
                bundle.getInt(PRICE, 0),
                bundle.getString(OPTIONS));

        harmonica.setId(bundle.getInt(ID, 0));

        binding.addHarmonicaImage.setImageBitmap(BitmapFactory.decodeByteArray(harmonica.getIcon(), 0, harmonica.getIcon().length));
        binding.inputHarmonicaType.setText(harmonica.getType());
        binding.inputHarmonicaTone.setText(harmonica.getTone());
        binding.inputHarmonicaRange.setText(harmonica.getRange());
        binding.inputHarmonicaPrice.setText(harmonica.getPrice());
        binding.inputHarmonicaOptions.setText(harmonica.getOptions());
        binding.addHarmonicaToCatalog.setText("Сохранить изменения");

    }
}