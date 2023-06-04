package com.example.harmonicatulashop.ui.account.fragments;

import static com.example.harmonicatulashop.models.harmonica.Bayan.ICON;
import static com.example.harmonicatulashop.models.harmonica.Bayan.ID;
import static com.example.harmonicatulashop.models.harmonica.Bayan.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Bayan.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.RANGE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.TYPE;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.databinding.FragmentAddBayanBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.ui.account.viewmodels.AddBayanViewModel;

public class AddBayanFragment extends Fragment {

    private AddBayanViewModel viewModel;
    private FragmentAddBayanBinding binding;

    private static boolean editing;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(AddBayanViewModel.class);

        binding = FragmentAddBayanBinding.inflate(inflater, container, false);
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
            setEditing(false);
            return;
        }

        setEditing(true);

        ActionBar actionBar = MainActivity.INSTANCE.getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(false);

        Bayan bayan = new Bayan(bundle.getByteArray(ICON),
                bundle.getString(TYPE),
                bundle.getString(RANGE),
                bundle.getInt(PRICE, 0),
                bundle.getString(OPTIONS));

        bayan.setId(bundle.getInt(ID, 0));

        viewModel.setBayan(bayan);

        binding.addBayanImage.setImageBitmap(BitmapFactory.decodeByteArray(bayan.getIcon(), 0, bayan.getIcon().length));
        binding.addBayanImage.setClickable(false);
        binding.addBayanImageIcon.setVisibility(View.GONE);
        binding.inputBayanType.setText(bayan.getType());
        binding.inputBayanRange.setText(bayan.getRange());
        binding.inputBayanPrice.setText(String.valueOf(bayan.getPrice()));
        binding.inputBayanOptions.setText(bayan.getOptions());
        binding.addBayanToCatalog.setText("Сохранить изменения");

    }

    public static boolean isEditing() {
        return editing;
    }

    private static void setEditing(boolean editing) {
        AddBayanFragment.editing = editing;
    }
}