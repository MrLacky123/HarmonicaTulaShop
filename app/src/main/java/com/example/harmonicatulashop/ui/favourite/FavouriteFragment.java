package com.example.harmonicatulashop.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentFavouriteBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.harmonica.adapters.AccordionAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.HarmonicaAdapter;

import java.util.List;

public class FavouriteFragment extends Fragment {

    private FavouriteViewModel viewModel;

    private FragmentFavouriteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(FavouriteViewModel.class);

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.setBinding(binding);

        setHarmonicaListView();

        setBayanListView();

        setAccordionListView();

        return binding.getRoot();
    }

    public void setHarmonicaListView () {

        RecyclerView harmonicaFavouriteList = binding.harmonicaFavouriteList;

        final HarmonicaAdapter harmonicaAdapter = new HarmonicaAdapter(new HarmonicaAdapter.HarmonicaDiff());
        harmonicaFavouriteList.setAdapter(harmonicaAdapter);
        harmonicaFavouriteList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllHarmonicas().observe(MainActivity.INSTANCE, harmonicaAdapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                FavouriteRepository favouriteRepository = new FavouriteRepository(MainActivity.INSTANCE.getApplication());
                favouriteRepository.deleteHarmonica((int) viewHolder.itemView.getTag());

                if (viewModel.getAllHarmonicas().getValue() == null) {

                    binding.harmonicaFavouriteList.setVisibility(View.GONE);
                    binding.harmonicaEmpty.setVisibility(View.VISIBLE);

                } else if (viewModel.getAllHarmonicas().getValue().size() == 1) {

                    binding.harmonicaFavouriteList.setVisibility(View.GONE);
                    binding.harmonicaEmpty.setVisibility(View.VISIBLE);

                }
            }
        }).attachToRecyclerView(harmonicaFavouriteList);
    }

    public void setBayanListView() {

        RecyclerView bayanFavouriteList = binding.bayanFavouriteList;

        final BayanAdapter bayanAdapter = new BayanAdapter(new BayanAdapter.BayanDiff());
        bayanFavouriteList.setAdapter(bayanAdapter);
        bayanFavouriteList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllBayans().observe(MainActivity.INSTANCE, bayanAdapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                FavouriteRepository favouriteRepository = new FavouriteRepository(MainActivity.INSTANCE.getApplication());
                favouriteRepository.deleteBayan((int) viewHolder.itemView.getTag());

                if (viewModel.getAllBayans().getValue() == null) {

                    binding.bayanFavouriteList.setVisibility(View.GONE);
                    binding.bayanEmpty.setVisibility(View.VISIBLE);

                } else if (viewModel.getAllBayans().getValue().size() == 1) {

                    binding.bayanFavouriteList.setVisibility(View.GONE);
                    binding.bayanEmpty.setVisibility(View.VISIBLE);

                }
            }
        }).attachToRecyclerView(bayanFavouriteList);
    }

    public void setAccordionListView() {

        RecyclerView accordionFavouriteList = binding.accordionFavouriteList;

        final AccordionAdapter accordionAdapter = new AccordionAdapter(new AccordionAdapter.AccordionDiff());
        accordionFavouriteList.setAdapter(accordionAdapter);
        accordionFavouriteList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllAccordions().observe(MainActivity.INSTANCE, accordionAdapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                FavouriteRepository favouriteRepository = new FavouriteRepository(MainActivity.INSTANCE.getApplication());
                favouriteRepository.deleteAccordion((int) viewHolder.itemView.getTag());

                if (viewModel.getAllAccordions().getValue() == null) {

                    binding.accordionFavouriteList.setVisibility(View.GONE);
                    binding.accordionEmpty.setVisibility(View.VISIBLE);

                } else if (viewModel.getAllAccordions().getValue().size() == 1) {

                    binding.accordionFavouriteList.setVisibility(View.GONE);
                    binding.accordionEmpty.setVisibility(View.VISIBLE);

                }
            }
        }).attachToRecyclerView(accordionFavouriteList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}