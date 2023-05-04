package com.example.harmonicatulashop.ui.catalog.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.databinding.ActivityAccordionBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.ui.catalog.viewmodels.AccordionActivityViewModel;

public class AccordionActivity extends AppCompatActivity {

    public static AccordionActivity INSTANCE;

    public static final String ID = "com.example.harmonicatulashop.ID";
    public static final String ICON = "com.example.harmonicatulashop.ICON";
    public static final String RANGE = "com.example.harmonicatulashop.RANGE";
    public static final String PRICE = "com.example.harmonicatulashop.PRICE";
    public static final String OPTIONS = "com.example.harmonicatulashop.OPTIONS";

    private ActivityAccordionBinding binding;

    private AccordionActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        INSTANCE = this;

        viewModel = new ViewModelProvider(this).get(AccordionActivityViewModel.class);

        binding = ActivityAccordionBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        setContentView(binding.getRoot());

        Intent intent = getIntent();

        Accordion accordion = new Accordion(intent.getByteArrayExtra(ICON),
                intent.getStringExtra(RANGE),
                intent.getIntExtra(PRICE, 0),
                intent.getStringExtra(OPTIONS));

        accordion.setId(intent.getIntExtra(ID, 0));

        viewModel.setBinding(binding);
        viewModel.setAccordion(accordion);

        bindView(accordion);
    }

    public void bindView(Accordion accordion) {

        Bitmap bitmap = BitmapFactory.decodeByteArray(accordion.getIcon(), 0, accordion.getIcon().length);
        binding.accordionImage.setImageBitmap(bitmap);

        String title = Bayan.NAME + " " + accordion.getRange();
        binding.accordionTitle.setText(title);

        String price = accordion.getPrice() + "руб.";
        binding.price.setText(price);

        binding.range.append(accordion.getRange());
        binding.accordionOptions.setText(accordion.getOptions());
    }
}