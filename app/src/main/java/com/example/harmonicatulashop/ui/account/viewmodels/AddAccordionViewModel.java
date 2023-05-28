package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_ACCORDION;
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
import com.example.harmonicatulashop.databinding.FragmentAddAccordionBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.google.common.collect.Iterables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddAccordionViewModel extends ViewModel {

    private FragmentAddAccordionBinding binding;

    public void setBinding(FragmentAddAccordionBinding binding) {
        this.binding = binding;
    }

    public void addAccordionToCatalog() {

        ImageView accordionIcon = binding.addAccordionImage;

        EditText inputRange = binding.inputAccordionRange;
        String range = inputRange.getText().toString();

        EditText inputPrice = binding.inputAccordionPrice;
        String stringPrice = inputPrice.getText().toString();

        if (range.equals("") || stringPrice.equals("") || accordionIcon.getDrawable() == null) {

            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Все параметры аккордеона должны быть заполнены", Toast.LENGTH_SHORT).show();

            return;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        Bitmap bitmap = MainActivity.getAddedAccordionImage();
        bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, false);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] blob = bos.toByteArray();

        try {
            bos.close();
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }

        int price = Integer.parseInt(stringPrice);

        Accordion accordion = new Accordion(blob, range, price, binding.inputAccordionOptions.getText().toString());
        new CatalogRepository(MainActivity.INSTANCE.getApplication()).insertAccordion(accordion);

        MainActivity.INSTANCE.setFragment(previousFragmentMap.get("Добавить аккордеон"),
                currentLayout, null,
                Iterables.getLast(previousTitles), null);

    }

    public void addAccordionIcon() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        MainActivity.INSTANCE.startActivityForResult(photoPickerIntent, GALLERY_REQUEST_FOR_ACCORDION);

    }
}