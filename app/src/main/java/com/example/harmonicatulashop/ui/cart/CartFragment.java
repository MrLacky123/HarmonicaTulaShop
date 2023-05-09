package com.example.harmonicatulashop.ui.cart;

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
import com.example.harmonicatulashop.databinding.FragmentCartBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.harmonica.adapters.AccordionAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.HarmonicaAdapter;

import java.util.List;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    private CartViewModel cartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        binding.setViewModel(cartViewModel);
        binding.executePendingBindings();

        cartViewModel.setBinding(binding);

        setHarmonicaListView();

        setBayanListView();

        setAccordionListView();

        return binding.getRoot();
    }

    public void setHarmonicaListView() {

        RecyclerView harmonicaCartList = binding.harmonicaCartList;

        HarmonicaAdapter harmonicaAdapter = new HarmonicaAdapter(new HarmonicaAdapter.HarmonicaDiff());
        harmonicaCartList.setAdapter(harmonicaAdapter);
        harmonicaCartList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        cartViewModel.getAllHarmonicas().observe(MainActivity.INSTANCE, harmonicaAdapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
                cartRepository.deleteHarmonica((int) viewHolder.itemView.getTag());

                if (cartViewModel.getAllHarmonicas().getValue() == null) {

                    binding.harmonicaCartList.setVisibility(View.GONE);
                    binding.harmonicaEmpty.setVisibility(View.VISIBLE);

                } else if (cartViewModel.getAllHarmonicas().getValue().size() == 1) {

                    binding.harmonicaCartList.setVisibility(View.GONE);
                    binding.harmonicaEmpty.setVisibility(View.VISIBLE);

                }
            }
        }).attachToRecyclerView(harmonicaCartList);

    }

    public void setBayanListView() {

        RecyclerView bayanCartList = binding.bayanCartList;

        BayanAdapter bayanAdapter = new BayanAdapter(new BayanAdapter.BayanDiff());
        bayanCartList.setAdapter(bayanAdapter);
        bayanCartList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        cartViewModel.getAllBayans().observe(MainActivity.INSTANCE, bayanAdapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
                cartRepository.deleteBayan((int) viewHolder.itemView.getTag());

                if (cartViewModel.getAllBayans().getValue() == null) {

                    binding.bayanCartList.setVisibility(View.GONE);
                    binding.bayanEmpty.setVisibility(View.VISIBLE);

                } else if (cartViewModel.getAllBayans().getValue().size() == 1) {

                    binding.bayanCartList.setVisibility(View.GONE);
                    binding.bayanEmpty.setVisibility(View.VISIBLE);

                }
            }
        }).attachToRecyclerView(bayanCartList);
    }

    public void setAccordionListView() {

        RecyclerView accordionCartList = binding.accordionCartList;

        AccordionAdapter accordionAdapter = new AccordionAdapter(new AccordionAdapter.AccordionDiff());
        accordionCartList.setAdapter(accordionAdapter);
        accordionCartList.setLayoutManager(new LinearLayoutManager(MainActivity.INSTANCE));

        cartViewModel.getAllAccordions().observe(MainActivity.INSTANCE, accordionAdapter::submitList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                CartRepository cartRepository = new CartRepository(MainActivity.INSTANCE.getApplication());
                cartRepository.deleteAccordion((int) viewHolder.itemView.getTag());

                if (cartViewModel.getAllAccordions().getValue() == null) {

                    binding.accordionCartList.setVisibility(View.GONE);
                    binding.accordionEmpty.setVisibility(View.VISIBLE);

                } else if (cartViewModel.getAllAccordions().getValue().size() == 1) {

                    binding.accordionCartList.setVisibility(View.GONE);
                    binding.accordionEmpty.setVisibility(View.VISIBLE);

                }
            }
        }).attachToRecyclerView(accordionCartList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}