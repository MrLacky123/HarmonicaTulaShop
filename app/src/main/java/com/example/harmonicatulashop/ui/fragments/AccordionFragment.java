package com.example.harmonicatulashop.ui.fragments;

import static com.example.harmonicatulashop.models.harmonica.Accordion.ICON;
import static com.example.harmonicatulashop.models.harmonica.Accordion.ID;
import static com.example.harmonicatulashop.models.harmonica.Accordion.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Accordion.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Accordion.RANGE;

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
import com.example.harmonicatulashop.databinding.FragmentAccordionBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;

public class AccordionFragment extends Fragment {

    private AccordionViewModel viewModel;
    private FragmentAccordionBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(AccordionViewModel.class);

        binding = FragmentAccordionBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        Bundle bundle = getArguments();

        assert bundle != null;
        Accordion accordion = new Accordion(bundle.getByteArray(ICON),
                bundle.getString(RANGE),
                bundle.getInt(PRICE, 0),
                bundle.getString(OPTIONS));

        accordion.setId(bundle.getInt(ID, 0));

        viewModel.setAccordion(accordion);
        viewModel.setBinding(binding);

        bindView(accordion);

        return binding.getRoot();
    }

    public void bindView(Accordion accordion){

        Bitmap bitmap = BitmapFactory.decodeByteArray(accordion.getIcon(), 0, accordion.getIcon().length);
        binding.accordionImage.setImageBitmap(bitmap);

        String title = Accordion.NAME + " " + accordion.getRange();
        binding.accordionTitle.setText(title);

        if (MainActivity.currentAdmin != null) {
            binding.addAccordionToCart.setText("Удалить");
            binding.addAccordionToFavourites.setText("Редактировать");
        }

        String price = accordion.getPrice() + " руб.";
        binding.price.setText(price);

        binding.range.append(" " + accordion.getRange());
        binding.accordionOptions.setText(accordion.getOptions());

    }
}