package com.example.harmonicatulashop.ui.catalog.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.databinding.ActivityHarmonicaBinding;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.ui.catalog.viewmodels.HarmonicaActivityViewModel;

public class HarmonicaActivity extends AppCompatActivity {

    public static HarmonicaActivity INSTANCE;

    public static final String ID = "com.example.harmonicatulashop.ID";
    public static final String ICON = "com.example.harmonicatulashop.ICON";
    public static final String TYPE = "com.example.harmonicatulashop.TYPE";
    public static final String TONE = "com.example.harmonicatulashop.TONE";
    public static final String RANGE = "com.example.harmonicatulashop.RANGE";
    public static final String PRICE = "com.example.harmonicatulashop.PRICE";
    public static final String OPTIONS = "com.example.harmonicatulashop.OPTIONS";

    private ActivityHarmonicaBinding binding;
    private HarmonicaActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        INSTANCE = this;

        viewModel = new ViewModelProvider(this).get(HarmonicaActivityViewModel.class);

        binding = ActivityHarmonicaBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.setBinding(binding);

        setContentView(binding.getRoot());

        Intent intent = getIntent();

        Harmonica harmonica = new Harmonica(intent.getByteArrayExtra(ICON),
                intent.getStringExtra(TYPE),
                intent.getStringExtra(TONE),
                intent.getStringExtra(RANGE),
                intent.getIntExtra(PRICE, 0),
                intent.getStringExtra(OPTIONS));

        harmonica.setId(intent.getIntExtra(ID, 0));

        viewModel.setHarmonica(harmonica);

        bindView(harmonica);

        setTitle(Harmonica.NAME + " \"" + harmonica.getType() + "\"");
    }

    public void bindView(Harmonica harmonica){

        Bitmap bitmap = BitmapFactory.decodeByteArray(harmonica.getIcon(), 0, harmonica.getIcon().length);
        binding.harmonicaImage.setImageBitmap(bitmap);

        String title = Harmonica.NAME + " \"" + harmonica.getType() + "\" " + harmonica.getRange();
        binding.harmonicaTitle.setText(title);

        String price = harmonica.getPrice() + " руб.";
        binding.price.setText(price);

        binding.tone.append(" " + harmonica.getTone());
        binding.range.append(" " + harmonica.getRange());
        binding.harmonicaOptions.setText(harmonica.getOptions());
    }
}