package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_ACCORDION;
import static com.example.harmonicatulashop.MainActivity.currentLayout;
import static com.example.harmonicatulashop.MainActivity.previousFragmentMap;
import static com.example.harmonicatulashop.MainActivity.previousTitles;
import static com.example.harmonicatulashop.models.harmonica.Accordion.ICON;
import static com.example.harmonicatulashop.models.harmonica.Accordion.ID;
import static com.example.harmonicatulashop.models.harmonica.Accordion.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Accordion.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Accordion.RANGE;

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
import com.example.harmonicatulashop.databinding.FragmentAddAccordionBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.ui.account.fragments.AddAccordionFragment;
import com.example.harmonicatulashop.ui.fragments.AccordionFragment;
import com.google.common.collect.Iterables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddAccordionViewModel extends ViewModel {

    private FragmentAddAccordionBinding binding;

    private Accordion accordion;

    public void setBinding(FragmentAddAccordionBinding binding) {
        this.binding = binding;
    }

    public void addAccordionToCatalog() {

        ImageView accordionIcon = binding.addAccordionImage;

        EditText inputRange = binding.inputAccordionRange;
        String range = inputRange.getText().toString();

        EditText inputPrice = binding.inputAccordionPrice;
        String stringPrice = inputPrice.getText().toString();

        EditText inputOptions = binding.inputAccordionOptions;
        String options = inputOptions.getText().toString();

        if (range.equals("") || stringPrice.equals("") || accordionIcon.getDrawable() == null) {

            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Все параметры аккордеона должны быть заполнены", Toast.LENGTH_SHORT).show();

            return;
        }

        int price = Integer.parseInt(stringPrice);

        accordion.setRange(range);
        accordion.setPrice(price);
        accordion.setOptions(options);

        if (AddAccordionFragment.isEditing()) {

            String title = Accordion.NAME + " " + accordion.getRange();

            Class<? extends Fragment> f = previousFragmentMap.get(Iterables.getLast(previousTitles));
            previousFragmentMap.remove(Iterables.getLast(previousTitles));

            previousTitles.remove(Iterables.getLast(previousTitles));
            previousTitles.add(title);

            previousFragmentMap.put(Iterables.getLast(previousTitles), f);

            Bundle bundle = new Bundle();

            bundle.putInt(ID, accordion.getId());
            bundle.putByteArray(ICON, accordion.getIcon());
            bundle.putString(RANGE, accordion.getRange());
            bundle.putInt(PRICE, accordion.getPrice());
            bundle.putString(OPTIONS, accordion.getOptions());

            new CatalogRepository(MainActivity.INSTANCE.getApplication()).replaceAccordion(accordion.getId(), accordion);

            MainActivity.INSTANCE.setFragment(AccordionFragment.class,
                    currentLayout, bundle,
                    title,
                    null);

        } else {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            Bitmap bitmap = MainActivity.getAddedAccordionImage();
            bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, false);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] blob = bos.toByteArray();

            try {
                bos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Accordion a = new Accordion(blob, range, price, options);
            new CatalogRepository(MainActivity.INSTANCE.getApplication()).insertAccordion(a);

            MainActivity.INSTANCE.setFragment(previousFragmentMap.get("Добавить аккордеон"),
                    currentLayout, null,
                    Iterables.getLast(previousTitles), null);
        }

    }

    public void addAccordionIcon() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        MainActivity.INSTANCE.startActivityForResult(photoPickerIntent, GALLERY_REQUEST_FOR_ACCORDION);

    }

    public void setAccordion(Accordion accordion) {
        this.accordion = accordion;
    }
}