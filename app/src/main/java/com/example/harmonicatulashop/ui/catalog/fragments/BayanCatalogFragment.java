package com.example.harmonicatulashop.ui.catalog.fragments;

import static com.example.harmonicatulashop.models.harmonica.Bayan.ICON;
import static com.example.harmonicatulashop.models.harmonica.Bayan.ID;
import static com.example.harmonicatulashop.models.harmonica.Bayan.OPTIONS;
import static com.example.harmonicatulashop.models.harmonica.Bayan.PRICE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.RANGE;
import static com.example.harmonicatulashop.models.harmonica.Bayan.TYPE;

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
import com.example.harmonicatulashop.databinding.FragmentBayanCatalogBinding;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;
import com.example.harmonicatulashop.ui.catalog.viewmodels.BayanCatalogViewModel;
import com.example.harmonicatulashop.ui.fragments.BayanFragment;

public class BayanCatalogFragment extends Fragment {

    private BayanCatalogViewModel viewModel;
    private FragmentBayanCatalogBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(BayanCatalogViewModel.class);

        binding = FragmentBayanCatalogBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        RecyclerView recyclerView = binding.bayanList;
        final BayanAdapter adapter = new BayanAdapter(new BayanAdapter.BayanDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        viewModel.getAllBayans().observe(MainActivity.INSTANCE, adapter::submitList);

        adapter.setOnItemClickListener(bayan -> {

            String title = Bayan.NAME + " \"" + bayan.getType() + "\"";

            Bundle bundle = new Bundle();

            bundle.putInt(ID, bayan.getId());
            bundle.putByteArray(ICON, bayan.getIcon());
            bundle.putString(TYPE, bayan.getType());
            bundle.putString(RANGE, bayan.getRange());
            bundle.putInt(PRICE, bayan.getPrice());
            bundle.putString(OPTIONS, bayan.getOptions());

            MainActivity.INSTANCE.setFragment(BayanFragment.class, R.id.bayan_catalog_layout, bundle, title);

        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}