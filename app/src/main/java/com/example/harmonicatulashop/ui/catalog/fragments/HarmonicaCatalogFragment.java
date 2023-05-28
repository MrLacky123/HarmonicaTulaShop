package com.example.harmonicatulashop.ui.catalog.fragments;

import static com.example.harmonicatulashop.models.harmonica.Harmonica.ICON;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.ID;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.RANGE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.TONE;
import static com.example.harmonicatulashop.models.harmonica.Harmonica.TYPE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.FragmentHarmonicaCatalogBinding;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.harmonica.adapters.HarmonicaAdapter;
import com.example.harmonicatulashop.ui.catalog.viewmodels.HarmonicaCatalogViewModel;
import com.example.harmonicatulashop.ui.fragments.HarmonicaFragment;

public class HarmonicaCatalogFragment extends Fragment {

    public static HarmonicaCatalogFragment INSTANCE;

    private FragmentHarmonicaCatalogBinding catalogBinding;

    private HarmonicaCatalogViewModel catalogViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity.currentLayout = getId();

        INSTANCE = this;

        catalogViewModel = new ViewModelProvider(MainActivity.INSTANCE).get(HarmonicaCatalogViewModel.class);

        catalogBinding = FragmentHarmonicaCatalogBinding.inflate(inflater, container, false);
        catalogBinding.setViewmodel(catalogViewModel);
        catalogBinding.setLifecycleOwner(this);

        RecyclerView recyclerView = catalogBinding.harmonicaList;
        final HarmonicaAdapter adapter = new HarmonicaAdapter(new HarmonicaAdapter.HarmonicaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        catalogViewModel.getAllHarmonicas().observe(MainActivity.INSTANCE, adapter::submitList);

        adapter.setOnItemClickListener(harmonica -> {

            String title = Harmonica.NAME + " \"" + harmonica.getType() + "\"";

            Bundle bundle = new Bundle();

            bundle.putInt(ID, harmonica.getId());
            bundle.putByteArray(ICON, harmonica.getIcon());
            bundle.putString(TYPE, harmonica.getType());
            bundle.putString(TONE, harmonica.getTone());
            bundle.putString(RANGE, harmonica.getRange());
            bundle.putInt(PRICE, harmonica.getPrice());
            bundle.putString(OPTIONS, harmonica.getOptions());

            MainActivity.INSTANCE.setFragment(HarmonicaFragment.class,
                    R.id.harmonica_catalog_layout, bundle,
                    title, HarmonicaCatalogFragment.class);

        });

        return catalogBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        catalogBinding = null;
    }
}