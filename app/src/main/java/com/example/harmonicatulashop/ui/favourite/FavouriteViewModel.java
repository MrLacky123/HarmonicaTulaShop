package com.example.harmonicatulashop.ui.favourite;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.favourite.room.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentFavouriteBinding;
import com.example.harmonicatulashop.models.favourite.Accordion;
import com.example.harmonicatulashop.models.favourite.Bayan;
import com.example.harmonicatulashop.models.favourite.Harmonica;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {

    public LiveData<List<Harmonica>> harmonicas;
    public LiveData<List<Accordion>> accordions;
    public LiveData<List<Bayan>> bayans;

    private final FavouriteRepository favouriteRepository;

    private FragmentFavouriteBinding binding;

    public FavouriteViewModel (Application application) {
        super(application);

        favouriteRepository = new FavouriteRepository(application);
        harmonicas = favouriteRepository.getAllHarmonicas();
        bayans = favouriteRepository.getAllBayans();
        accordions = favouriteRepository.getAllAccordions();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() { return harmonicas; }

    public void insertHarmonica(Harmonica harmonica) { favouriteRepository.insertHarmonica(harmonica); }

    public LiveData<List<Bayan>> getAllBayans() { return bayans; }

    public void insertBayan(Bayan bayan) { favouriteRepository.insertBayan(bayan); }

    public LiveData<List<Accordion>> getAllAccordions() { return accordions; }

    public void insertAccordion(Accordion accordion) { favouriteRepository.insertAccordion(accordion); }

    public void onHarmonicaClick() {
        RecyclerView view  = binding.harmonicaFavouriteList;
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
        RecyclerView view = binding.bayanFavouriteList;
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
        RecyclerView view = binding.accordionFavouriteList;
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

    public void setBinding(FragmentFavouriteBinding binding) {
        this.binding = binding;
    }
}