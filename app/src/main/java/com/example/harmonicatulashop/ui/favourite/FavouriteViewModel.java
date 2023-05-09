package com.example.harmonicatulashop.ui.favourite;

import android.app.Application;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentFavouriteBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

import java.util.List;
import java.util.Objects;

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

        if (harmonicas.getValue() != null && !harmonicas.getValue().isEmpty()) {

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
        } else {

            TextView textView = binding.harmonicaEmpty;

            switch (textView.getVisibility()) {
                case View.VISIBLE:
                    textView.setVisibility(View.GONE);
                    binding.harmonicaExpand.setImageResource(R.drawable.baseline_expand_more_24);
                    break;
                case View.GONE:
                    textView.setVisibility(View.VISIBLE);
                    binding.harmonicaExpand.setImageResource(R.drawable.baseline_expand_less_24);
                    break;
                case View.INVISIBLE:
                    break;
            }
        }
    }

    public void onBayanClick() {

        if (bayans.getValue() != null && !bayans.getValue().isEmpty()) {

            RecyclerView view  = binding.bayanFavouriteList;

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
        } else {

            TextView textView = binding.bayanEmpty;

            switch (textView.getVisibility()) {
                case View.VISIBLE:
                    textView.setVisibility(View.GONE);
                    binding.bayanExpand.setImageResource(R.drawable.baseline_expand_more_24);
                    break;
                case View.GONE:
                    textView.setVisibility(View.VISIBLE);
                    binding.bayanExpand.setImageResource(R.drawable.baseline_expand_less_24);
                    break;
                case View.INVISIBLE:
                    break;
            }
        }
    }

    public void onAccordionClick() {

        if (accordions.getValue() != null && !accordions.getValue().isEmpty()) {

            RecyclerView view  = binding.accordionFavouriteList;

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
        } else {

            TextView textView = binding.accordionEmpty;

            switch (textView.getVisibility()) {
                case View.VISIBLE:
                    textView.setVisibility(View.GONE);
                    binding.accordionExpand.setImageResource(R.drawable.baseline_expand_more_24);
                    break;
                case View.GONE:
                    textView.setVisibility(View.VISIBLE);
                    binding.accordionExpand.setImageResource(R.drawable.baseline_expand_less_24);
                    break;
                case View.INVISIBLE:
                    break;
            }
        }
    }

    public void setBinding(FragmentFavouriteBinding binding) {
        this.binding = binding;
    }
}