package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_BAYAN;
import static com.example.harmonicatulashop.MainActivity.currentLayout;
import static com.example.harmonicatulashop.MainActivity.previousFragmentMap;
import static com.example.harmonicatulashop.MainActivity.previousTitles;
import static com.example.harmonicatulashop.models.harmonica.Bayan.ICON;
import static com.example.harmonicatulashop.models.harmonica.Bayan.ID;
import static com.example.harmonicatulashop.models.harmonica.Bayan.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Bayan.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.RANGE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.TYPE;

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
import com.example.harmonicatulashop.databinding.FragmentAddBayanBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.ui.account.fragments.AddBayanFragment;
import com.example.harmonicatulashop.ui.fragments.BayanFragment;
import com.example.harmonicatulashop.ui.fragments.HarmonicaFragment;
import com.google.common.collect.Iterables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddBayanViewModel extends ViewModel {

    private FragmentAddBayanBinding binding;

    private Bayan bayan;

    public void setBinding(FragmentAddBayanBinding binding) {
        this.binding = binding;
    }

    public void addBayanToCatalog() {

        ImageView bayanIcon = binding.addBayanImage;

        EditText inputType = binding.inputBayanType;
        String type = inputType.getText().toString();

        EditText inputRange = binding.inputBayanRange;
        String range = inputRange.getText().toString();

        EditText inputPrice = binding.inputBayanPrice;
        String stringPrice = inputPrice.getText().toString();

        EditText inputOptions = binding.inputBayanOptions;
        String options = inputOptions.getText().toString();

        if (type.equals("") || range.equals("") || stringPrice.equals("")
                || bayanIcon.getDrawable() == null) {

            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Все параметры баяна должны быть заполнены", Toast.LENGTH_SHORT).show();

            return;

        }

        int price = Integer.parseInt(stringPrice);

        bayan.setType(type);
        bayan.setRange(range);
        bayan.setPrice(price);
        bayan.setOptions(options);

        if (AddBayanFragment.isEditing()) {

            String title = Bayan.NAME + " \"" + bayan.getType() + "\"";

            Class<? extends Fragment> f = previousFragmentMap.get(Iterables.getLast(previousTitles));
            previousFragmentMap.remove(Iterables.getLast(previousTitles));

            previousTitles.remove(Iterables.getLast(previousTitles));
            previousTitles.add(title);

            previousFragmentMap.put(Iterables.getLast(previousTitles), f);

            Bundle bundle = new Bundle();

            bundle.putInt(ID, bayan.getId());
            bundle.putByteArray(ICON, bayan.getIcon());
            bundle.putString(TYPE, bayan.getType());
            bundle.putString(RANGE, bayan.getRange());
            bundle.putInt(PRICE, bayan.getPrice());
            bundle.putString(OPTIONS, bayan.getOptions());

            new CatalogRepository(MainActivity.INSTANCE.getApplication()).replaceBayan(bayan.getId(), bayan);

            MainActivity.INSTANCE.setFragment(BayanFragment.class,
                    currentLayout, bundle,
                    title,
                    null);

        } else {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            Bitmap bitmap = MainActivity.getAddedBayanImage();
            bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, false);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] blob = bos.toByteArray();

            try {
                bos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Bayan b = new Bayan(blob, type, range, price, options);
            new CatalogRepository(MainActivity.INSTANCE.getApplication()).insertBayan(b);

            MainActivity.INSTANCE.setFragment(previousFragmentMap.get("Добавить баян"),
                    currentLayout, null,
                    Iterables.getLast(previousTitles), null);
        }

    }

    public void addBayanIcon() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        MainActivity.INSTANCE.startActivityForResult(photoPickerIntent, GALLERY_REQUEST_FOR_BAYAN);

    }

    public void setBayan(Bayan bayan) {
        this.bayan = bayan;
    }
}