package com.example.harmonicatulashop.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.harmonica.room.favourite.FavouriteRepository;
import com.example.harmonicatulashop.databinding.FragmentFavouriteBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.harmonica.adapters.AccordionAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.HarmonicaAdapter;
import com.example.harmonicatulashop.ui.fragments.AccordionFragment;
import com.example.harmonicatulashop.ui.fragments.BayanFragment;
import com.example.harmonicatulashop.ui.fragments.HarmonicaFragment;

public class FavouriteFragment extends Fragment {

    private FavouriteViewModel viewModel;

    private FragmentFavouriteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.previousTitles.clear();
        MainActivity.previousFragmentMap.clear();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(FavouriteViewModel.class);

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

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

        harmonicaAdapter.setOnItemClickListener(harmonica -> {

            String title = Harmonica.NAME + " \"" + harmonica.getType() + "\"";

            Bundle bundle = new Bundle();

            bundle.putInt(Harmonica.ID, harmonica.getId());
            bundle.putByteArray(Harmonica.ICON, harmonica.getIcon());
            bundle.putString(Harmonica.TYPE, harmonica.getType());
            bundle.putString(Harmonica.TONE, harmonica.getTone());
            bundle.putString(Harmonica.RANGE, harmonica.getRange());
            bundle.putInt(Harmonica.PRICE, harmonica.getPrice());
            bundle.putString(Harmonica.OPTIONS, harmonica.getOptions());

            MainActivity.INSTANCE.setFragment(HarmonicaFragment.class, R.id.favourite_layout, bundle, title, FavouriteFragment.class);

        });

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

        bayanAdapter.setOnItemClickListener(bayan -> {

            String title = Bayan.NAME + " \"" + bayan.getType() + "\"";

            Bundle bundle = new Bundle();

            bundle.putInt(Bayan.ID, bayan.getId());
            bundle.putByteArray(Bayan.ICON, bayan.getIcon());
            bundle.putString(Bayan.TYPE, bayan.getType());
            bundle.putString(Bayan.RANGE, bayan.getRange());
            bundle.putInt(Bayan.PRICE, bayan.getPrice());
            bundle.putString(Bayan.OPTIONS, bayan.getOptions());

            MainActivity.INSTANCE.setFragment(BayanFragment.class, R.id.favourite_layout, bundle, title, FavouriteFragment.class);

        });

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

        accordionAdapter.setOnItemClickListener(accordion -> {

            String title = Accordion.NAME + " " + accordion.getRange();

            Bundle bundle = new Bundle();

            bundle.putInt(Accordion.ID, accordion.getId());
            bundle.putByteArray(Accordion.ICON, accordion.getIcon());
            bundle.putString(Accordion.RANGE, accordion.getRange());
            bundle.putInt(Accordion.PRICE, accordion.getPrice());
            bundle.putString(Accordion.OPTIONS, accordion.getOptions());

            MainActivity.INSTANCE.setFragment(AccordionFragment.class, R.id.favourite_layout, bundle, title, FavouriteFragment.class);

        });

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