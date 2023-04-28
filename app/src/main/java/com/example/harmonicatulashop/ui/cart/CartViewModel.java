package com.example.harmonicatulashop.ui.cart;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.cart.room.CartRepository;
import com.example.harmonicatulashop.databinding.FragmentCartBinding;
import com.example.harmonicatulashop.models.cart.Accordion;
import com.example.harmonicatulashop.models.cart.Bayan;
import com.example.harmonicatulashop.models.cart.Harmonica;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    public LiveData<List<Harmonica>> harmonicas;
    public LiveData<List<Accordion>> accordions;
    public LiveData<List<Bayan>> bayans;

    private final CartRepository cartRepository;

    private FragmentCartBinding binding;

    public CartViewModel (Application application) {
        super(application);

        cartRepository = new CartRepository(application);
        harmonicas = cartRepository.getAllHarmonicas();
        bayans = cartRepository.getAllBayans();
        accordions = cartRepository.getAllAccordions();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() { return harmonicas; }

    public void insertHarmonica(Harmonica harmonica) { cartRepository.insertHarmonica(harmonica); }

    public LiveData<List<Bayan>> getAllBayans() { return bayans; }

    public void insertBayan(Bayan bayan) { cartRepository.insertBayan(bayan); }

    public LiveData<List<Accordion>> getAllAccordions() { return accordions; }

    public void insertAccordion(Accordion accordion) { cartRepository.insertAccordion(accordion); }

    public void onHarmonicaClick() {
        RecyclerView view  = binding.harmonicaCartList;
        switch (view.getVisibility()) {
            case View.VISIBLE:
                view.setVisibility(View.GONE);
                binding.harmonicaExpand.setImageResource(R.drawable.baseline_expand_more_24);
                break;
            case View.GONE:
                view.setVisibility(View.VISIBLE);
                binding.harmonicaExpand.setImageResource(R.drawable.baseline_expand_less_24);
                break;
            case View.INVISIBLE:
                break;
        }
    }

    public void onBayanClick() {
        RecyclerView view = binding.bayanCartList;
        switch (view.getVisibility()) {
            case View.VISIBLE:
                view.setVisibility(View.GONE);
                binding.bayanExpand.setImageResource(R.drawable.baseline_expand_more_24);
                break;
            case View.GONE:
                view.setVisibility(View.VISIBLE);
                binding.bayanExpand.setImageResource(R.drawable.baseline_expand_less_24);
                break;
            case View.INVISIBLE:
                break;
        }
    }

    public void onAccordionClick() {
        RecyclerView view = binding.accordionCartList;
        switch (view.getVisibility()) {
            case View.VISIBLE:
                view.setVisibility(View.GONE);
                binding.accordionExpand.setImageResource(R.drawable.baseline_expand_more_24);
                break;
            case View.GONE:
                view.setVisibility(View.VISIBLE);
                binding.accordionExpand.setImageResource(R.drawable.baseline_expand_less_24);
                break;
            case View.INVISIBLE:
                break;
        }
    }

    public void setBinding(FragmentCartBinding binding) {
        this.binding = binding;
    }
}