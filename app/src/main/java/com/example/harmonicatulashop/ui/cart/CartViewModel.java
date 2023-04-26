package com.example.harmonicatulashop.ui.cart;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.cart.room.AccordionCartRepository;
import com.example.harmonicatulashop.database.cart.room.BayanCartRepository;
import com.example.harmonicatulashop.database.cart.room.HarmonicaCartRepository;
import com.example.harmonicatulashop.databinding.FragmentCartBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    public LiveData<List<Harmonica>> harmonicas;
    public LiveData<List<Accordion>> accordions;
    public LiveData<List<Bayan>> bayans;

    private final HarmonicaCartRepository harmonicaRepository;
    private final BayanCartRepository bayanRepository;
    private final AccordionCartRepository accordionRepository;

    private FragmentCartBinding binding;

    public CartViewModel (Application application) {
        super(application);

        harmonicaRepository = new HarmonicaCartRepository(application);
        harmonicas = harmonicaRepository.getAllHarmonicas();

        bayanRepository = new BayanCartRepository(application);
        bayans = bayanRepository.getAllBayans();

        accordionRepository = new AccordionCartRepository(application);
        accordions = accordionRepository.getAllAccordions();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() { return harmonicas; }

    public void insertHarmonica(Harmonica harmonica) { harmonicaRepository.insert(harmonica); }

    public LiveData<List<Bayan>> getAllBayans() { return bayans; }

    public void insertBayan(Bayan bayan) { bayanRepository.insert(bayan); }

    public LiveData<List<Accordion>> getAllAccordions() { return accordions; }

    public void insertAccorion(Accordion accordion) { accordionRepository.insert(accordion); }

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