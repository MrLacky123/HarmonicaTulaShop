package com.example.harmonicatulashop.ui.catalog.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.ActivityBayanBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.ui.catalog.viewmodels.BayanActivityViewModel;

public class BayanActivity extends AppCompatActivity {

    public static BayanActivity INSTANCE;

    public static final String ID = "com.example.harmonicatulashop.ID";
    public static final String ICON = "com.example.harmonicatulashop.ICON";
    public static final String TYPE = "com.example.harmonicatulashop.TYPE";
    public static final String RANGE = "com.example.harmonicatulashop.RANGE";
    public static final String PRICE = "com.example.harmonicatulashop.PRICE";
    public static final String OPTIONS = "com.example.harmonicatulashop.OPTIONS";

    private ActivityBayanBinding binding;
    private BayanActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        INSTANCE = this;

        viewModel = new ViewModelProvider(this).get(BayanActivityViewModel.class);

        binding = ActivityBayanBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        setContentView(binding.getRoot());

        Intent intent = getIntent();

        Bayan bayan = new Bayan(intent.getByteArrayExtra(ICON),
                intent.getStringExtra(TYPE),
                intent.getStringExtra(RANGE),
                intent.getIntExtra(PRICE, 0),
                intent.getStringExtra(OPTIONS));

        bayan.setId(intent.getIntExtra(ID, 0));

        viewModel.setBinding(binding);
        viewModel.setBayan(bayan);

        bindView(bayan);

        setTitle(Bayan.NAME + " \"" + bayan.getType() + "\"");
    }

    public void bindView(Bayan bayan) {

        Bitmap bitmap = BitmapFactory.decodeByteArray(bayan.getIcon(), 0, bayan.getIcon().length);
        binding.bayanImage.setImageBitmap(bitmap);

        String title = Bayan.NAME + " \"" + bayan.getType() + "\"";
        binding.bayanTitle.setText(title);

        String price = bayan.getPrice() + "руб.";
        binding.price.setText(price);

        binding.range.append(bayan.getRange());
        binding.bayanOptions.setText(bayan.getOptions());
    }
}