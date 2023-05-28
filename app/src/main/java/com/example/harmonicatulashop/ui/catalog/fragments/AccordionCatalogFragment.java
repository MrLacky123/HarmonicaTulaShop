package com.example.harmonicatulashop.ui.catalog.fragments;

import static com.example.harmonicatulashop.models.harmonica.Accordion.ICON;
import static com.example.harmonicatulashop.models.harmonica.Accordion.ID;
import static com.example.harmonicatulashop.models.harmonica.Accordion.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Accordion.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Accordion.RANGE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentAccordionCatalogBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.adapters.AccordionAdapter;
import com.example.harmonicatulashop.ui.catalog.viewmodels.AccordionCatalogViewModel;
import com.example.harmonicatulashop.ui.fragments.AccordionFragment;

public class AccordionCatalogFragment extends Fragment {

    private AccordionCatalogViewModel viewModel;
    private FragmentAccordionCatalogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(AccordionCatalogViewModel.class);

        binding = FragmentAccordionCatalogBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        RecyclerView recyclerView = binding.accordionList;
        final AccordionAdapter adapter = new AccordionAdapter(new AccordionAdapter.AccordionDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllAccordions().observe(MainActivity.INSTANCE, adapter::submitList);

        adapter.setOnItemClickListener(accordion -> {

            String title = Accordion.NAME + " " + accordion.getRange();

            Bundle bundle = new Bundle();

            bundle.putInt(ID, accordion.getId());
            bundle.putByteArray(ICON, accordion.getIcon());
            bundle.putString(RANGE, accordion.getRange());
            bundle.putInt(PRICE, accordion.getPrice());
            bundle.putString(OPTIONS, accordion.getOptions());

            MainActivity.INSTANCE.setFragment(AccordionFragment.class, R.id.accordion_catalog_layout, bundle, title, AccordionCatalogFragment.class);

        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        ActionBar actionBar =  MainActivity.INSTANCE.getSupportActionBar();

        assert actionBar != null;
        actionBar.setTitle(R.string.title_accordion);
    }
}