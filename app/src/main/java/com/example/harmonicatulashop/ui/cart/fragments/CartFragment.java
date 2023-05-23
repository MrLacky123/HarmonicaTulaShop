package com.example.harmonicatulashop.ui.cart.fragments;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.databinding.FragmentCartBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.harmonica.adapters.AccordionAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;
import com.example.harmonicatulashop.models.harmonica.adapters.HarmonicaAdapter;
import com.example.harmonicatulashop.ui.cart.viewmodels.CartViewModel;
import com.example.harmonicatulashop.ui.fragments.AccordionFragment;
import com.example.harmonicatulashop.ui.fragments.BayanFragment;
import com.example.harmonicatulashop.ui.fragments.HarmonicaFragment;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    private CartViewModel cartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainActivity.previousTitles.clear();
        MainActivity.previousFragmentMap.clear();

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        binding.setViewModel(cartViewModel);
        binding.setLifecycleOwner(this);

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

        harmonicaAdapter.setOnItemClickListener(harmonica -> {
            String title = Harmonica.NAME + " \"" + harmonica.getType() + "\"";

            Bundle bundle = new Bundle();

            bundle.putInt(ID, harmonica.getId());
            bundle.putByteArray(ICON, harmonica.getIcon());
            bundle.putString(TYPE, harmonica.getType());
            bundle.putString(TONE, harmonica.getTone());
            bundle.putString(RANGE, harmonica.getRange());
            bundle.putInt(PRICE, harmonica.getPrice());
            bundle.putString(OPTIONS, harmonica.getOptions());

            MainActivity.INSTANCE.setFragment(HarmonicaFragment.class, R.id.cart_layout, bundle, title, CartFragment.class);

        });

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

        bayanAdapter.setOnItemClickListener(bayan -> {

            String title = Bayan.NAME + " \"" + bayan.getType() + "\"";

            Bundle bundle = new Bundle();

            bundle.putInt(Bayan.ID, bayan.getId());
            bundle.putByteArray(Bayan.ICON, bayan.getIcon());
            bundle.putString(Bayan.TYPE, bayan.getType());
            bundle.putString(Bayan.RANGE, bayan.getRange());
            bundle.putInt(Bayan.PRICE, bayan.getPrice());
            bundle.putString(Bayan.OPTIONS, bayan.getOptions());

            MainActivity.INSTANCE.setFragment(BayanFragment.class, R.id.cart_layout, bundle, title, CartFragment.class);

        });

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

        accordionAdapter.setOnItemClickListener(accordion -> {

            String title = Accordion.NAME + " " + accordion.getRange();

            Bundle bundle = new Bundle();

            bundle.putInt(Accordion.ID, accordion.getId());
            bundle.putByteArray(Accordion.ICON, accordion.getIcon());
            bundle.putString(Accordion.RANGE, accordion.getRange());
            bundle.putInt(Accordion.PRICE, accordion.getPrice());
            bundle.putString(Accordion.OPTIONS, accordion.getOptions());

            MainActivity.INSTANCE.setFragment(AccordionFragment.class, R.id.cart_layout, bundle, title, CartFragment.class);

        });

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