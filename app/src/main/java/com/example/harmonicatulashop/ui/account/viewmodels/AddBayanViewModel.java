package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_BAYAN;
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
import com.example.harmonicatulashop.databinding.FragmentAddBayanBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.google.common.collect.Iterables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddBayanViewModel extends ViewModel {

    private FragmentAddBayanBinding binding;

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

        if (type.equals("") || range.equals("") || stringPrice.equals("")
                || bayanIcon.getDrawable() == null) {

            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Все параметры баяна должны быть заполнены", Toast.LENGTH_SHORT).show();

            return;

        }

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

        int price = Integer.parseInt(stringPrice);

        Bayan bayan = new Bayan(blob, type, range, price, binding.inputBayanOptions.getText().toString());
        new CatalogRepository(MainActivity.INSTANCE.getApplication()).insertBayan(bayan);

        MainActivity.INSTANCE.setFragment(previousFragmentMap.get("Добавить баян"),
                currentLayout, null,
                Iterables.getLast(previousTitles), null);

    }

    public void addBayanIcon() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        MainActivity.INSTANCE.startActivityForResult(photoPickerIntent, GALLERY_REQUEST_FOR_BAYAN);

    }
}