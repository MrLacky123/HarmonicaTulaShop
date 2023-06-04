package com.example.harmonicatulashop.ui.account.fragments;

import static com.example.harmonicatulashop.models.harmonica.Accordion.ICON;
import static com.example.harmonicatulashop.models.harmonica.Accordion.ID;
import static com.example.harmonicatulashop.models.harmonica.Accordion.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Accordion.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Accordion.RANGE;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentAddAccordionBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.ui.account.viewmodels.AddAccordionViewModel;

public class AddAccordionFragment extends Fragment {

    private AddAccordionViewModel viewModel;
    private FragmentAddAccordionBinding binding;

    private static boolean editing;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(AddAccordionViewModel.class);

        binding = FragmentAddAccordionBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.setBinding(binding);

        Bundle bundle = getArguments();
        bindView(bundle);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void bindView(Bundle bundle) {

        if (bundle == null){
            setEditing(false);
            return;
        }

        setEditing(true);

        ActionBar actionBar = MainActivity.INSTANCE.getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(false);

        Accordion accordion = new Accordion(bundle.getByteArray(ICON),
                bundle.getString(RANGE),
                bundle.getInt(PRICE, 0),
                bundle.getString(OPTIONS));

        accordion.setId(bundle.getInt(ID, 0));

        viewModel.setAccordion(accordion);

        binding.addAccordionImage.setImageBitmap(BitmapFactory.decodeByteArray(accordion.getIcon(), 0, accordion.getIcon().length));
        binding.addAccordionImage.setClickable(false);
        binding.addAccordionImageIcon.setVisibility(View.GONE);
        binding.inputAccordionRange.setText(accordion.getRange());
        binding.inputAccordionPrice.setText(String.valueOf(accordion.getPrice()));
        binding.inputAccordionOptions.setText(accordion.getOptions());
        binding.addAccordionToCatalog.setText("Сохранить изменения");

    }

    public static boolean isEditing() {
        return editing;
    }

    public static void setEditing(boolean editing) {
        AddAccordionFragment.editing = editing;
    }
}