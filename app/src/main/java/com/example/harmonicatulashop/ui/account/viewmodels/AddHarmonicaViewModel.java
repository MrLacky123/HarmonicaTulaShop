package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_HARMONICA;
import static com.example.harmonicatulashop.MainActivity.currentLayout;
import static com.example.harmonicatulashop.MainActivity.previousFragmentMap;
import static com.example.harmonicatulashop.MainActivity.previousTitles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRepository;
import com.example.harmonicatulashop.databinding.FragmentAddHarmonicaBinding;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.ui.account.fragments.AccountAdminFragment;
import com.google.common.collect.Iterables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class AddHarmonicaViewModel extends ViewModel {

    private FragmentAddHarmonicaBinding binding;

    public void setBinding(FragmentAddHarmonicaBinding binding) {
        this.binding = binding;
    }

    public void addHarmonicaToCatalog() {

        ImageView harmonicaIcon = binding.addHarmonicaImage;

        EditText inputType = binding.inputHarmonicaType;
        String type = inputType.getText().toString();

        EditText inputTone = binding.inputHarmonicaTone;
        String tone = inputTone.getText().toString();

        EditText inputRange = binding.inputHarmonicaRange;
        String range = inputRange.getText().toString();

        EditText inputPrice = binding.inputHarmonicaPrice;
        String stringPrice = inputPrice.getText().toString();

        if (type.equals("") || tone.equals("") || range.equals("")
                || stringPrice.equals("") || harmonicaIcon.getDrawable() == null) {

            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Все параметры гармони должны быть заполнены", Toast.LENGTH_SHORT).show();

            return;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        Bitmap bitmap = MainActivity.getAddedHarmonicaImage();
        bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, false);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] blob = bos.toByteArray();

        try {
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int price = Integer.parseInt(stringPrice);

        Harmonica harmonica = new Harmonica(blob, type, tone, range, price, binding.inputHarmonicaOptions.getText().toString());
        new CatalogRepository(MainActivity.INSTANCE.getApplication()).insertHarmonica(harmonica);

        MainActivity.INSTANCE.setFragment(AccountAdminFragment.class,
                currentLayout, null,
                Iterables.getLast(previousTitles), null);

    }

    public void addHarmonicaIcon() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        MainActivity.INSTANCE.startActivityForResult(photoPickerIntent, GALLERY_REQUEST_FOR_HARMONICA);

    }
}