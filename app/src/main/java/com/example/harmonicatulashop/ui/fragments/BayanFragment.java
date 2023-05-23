package com.example.harmonicatulashop.ui.fragments;

import static com.example.harmonicatulashop.models.harmonica.Bayan.ICON;
import static com.example.harmonicatulashop.models.harmonica.Bayan.ID;
import static com.example.harmonicatulashop.models.harmonica.Bayan.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Bayan.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.RANGE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.TYPE;

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
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentBayanBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;

public class BayanFragment extends Fragment {

    private BayanViewModel viewModel;
    private FragmentBayanBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(BayanViewModel.class);

        binding = FragmentBayanBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        Bundle bundle = getArguments();

        assert bundle != null;
        Bayan bayan = new Bayan(bundle.getByteArray(ICON),
                bundle.getString(TYPE),
                bundle.getString(RANGE),
                bundle.getInt(PRICE, 0),
                bundle.getString(OPTIONS));

        bayan.setId(bundle.getInt(ID, 0));

        viewModel.setBayan(bayan);
        viewModel.setBinding(binding);

        bindView(bayan);

        return binding.getRoot();
    }

    public void bindView(Bayan bayan){

        Bitmap bitmap = BitmapFactory.decodeByteArray(bayan.getIcon(), 0, bayan.getIcon().length);
        binding.bayanImage.setImageBitmap(bitmap);

        String title = Bayan.NAME + " \"" + bayan.getType() + "\"";
        binding.bayanTitle.setText(title);

        if (MainActivity.currentAdmin != null) {
            binding.addBayanToCart.setText("Удалить");
            binding.addBayanToFavourites.setText("Редактировать");
        }

        String price = bayan.getPrice() + " руб.";
        binding.price.setText(price);

        binding.range.append(" " + bayan.getRange());
        binding.bayanOptions.setText(bayan.getOptions());
    }
}