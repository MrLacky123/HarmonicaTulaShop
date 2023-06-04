package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_HARMONICA;
import static com.example.harmonicatulashop.MainActivity.currentLayout;
import static com.example.harmonicatulashop.MainActivity.previousFragmentMap;
import static com.example.harmonicatulashop.MainActivity.previousTitles;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.ICON;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.ID;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.RANGE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.TONE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.TYPE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.harmonica.room.catalog.CatalogRepository;
import com.example.harmonicatulashop.databinding.FragmentAddHarmonicaBinding;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.ui.account.fragments.AccountAdminFragment;
import com.example.harmonicatulashop.ui.account.fragments.AddHarmonicaFragment;
import com.example.harmonicatulashop.ui.fragments.HarmonicaFragment;
import com.google.common.collect.Iterables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class AddHarmonicaViewModel extends ViewModel {

    private FragmentAddHarmonicaBinding binding;

    private Harmonica harmonica;

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

        EditText inputOptions = binding.inputHarmonicaOptions;
        String options = inputOptions.getText().toString();

        if (type.equals("") || tone.equals("") || range.equals("")
                || stringPrice.equals("") || harmonicaIcon.getDrawable() == null) {

            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Все параметры гармони должны быть заполнены", Toast.LENGTH_SHORT).show();

            return;
        }

        int price = Integer.parseInt(stringPrice);

        harmonica.setType(type);
        harmonica.setTone(tone);
        harmonica.setRange(range);
        harmonica.setPrice(price);
        harmonica.setOptions(options);

        if (AddHarmonicaFragment.isEditing()) {

            String title = Harmonica.NAME + " \"" + harmonica.getType() + "\"";

            Class<? extends Fragment> f = previousFragmentMap.get(Iterables.getLast(previousTitles));
            previousFragmentMap.remove(Iterables.getLast(previousTitles));

            previousTitles.remove(Iterables.getLast(previousTitles));
            previousTitles.add(title);

            previousFragmentMap.put(Iterables.getLast(previousTitles), f);

            Bundle bundle = new Bundle();

            bundle.putInt(ID, harmonica.getId());
            bundle.putByteArray(ICON, harmonica.getIcon());
            bundle.putString(TYPE, harmonica.getType());
            bundle.putString(TONE, harmonica.getTone());
            bundle.putString(RANGE, harmonica.getRange());
            bundle.putInt(PRICE, harmonica.getPrice());
            bundle.putString(OPTIONS, harmonica.getOptions());

            new CatalogRepository(MainActivity.INSTANCE.getApplication()).replaceHarmonica(harmonica.getId(), harmonica);

            MainActivity.INSTANCE.setFragment(HarmonicaFragment.class,
                    currentLayout, bundle,
                    title,
                    null);

        } else {

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

            Harmonica h = new Harmonica(blob, type, tone, range, price, options);

            new CatalogRepository(MainActivity.INSTANCE.getApplication()).insertHarmonica(h);

            MainActivity.INSTANCE.setFragment(AccountAdminFragment.class,
                    currentLayout, null,
                    Iterables.getLast(previousTitles), null);

        }
    }

    public void addHarmonicaIcon() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        MainActivity.INSTANCE.startActivityForResult(photoPickerIntent, GALLERY_REQUEST_FOR_HARMONICA);

    }

    public void setHarmonica(Harmonica harmonica) {
        this.harmonica = harmonica;
    }
}