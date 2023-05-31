package com.example.harmonicatulashop.ui.cart.viewmodels;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.harmonica.room.cart.CartRepository;
import com.example.harmonicatulashop.database.order.room.OrderRepository;
import com.example.harmonicatulashop.databinding.FragmentMakeOrderBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.order.Order;
import com.example.harmonicatulashop.ui.cart.fragments.CartFragment;
import com.example.harmonicatulashop.ui.cart.fragments.MakeOrderFragment;

import java.util.List;

public class MakeOrderViewModel extends AndroidViewModel {

    private final LiveData<List<Harmonica>> harmonicas;
    private final LiveData<List<Accordion>> accordions;
    private final LiveData<List<Bayan>> bayans;

    private FragmentMakeOrderBinding binding;

    public MakeOrderViewModel(Application application) {
        super(application);

        CartRepository cartRepository = new CartRepository(application);
        harmonicas = cartRepository.getAllHarmonicas();
        bayans = cartRepository.getAllBayans();
        accordions = cartRepository.getAllAccordions();
    }

    public LiveData<List<Harmonica>> getAllHarmonicas() { return harmonicas; }

    public LiveData<List<Bayan>> getAllBayans() { return bayans; }

    public LiveData<List<Accordion>> getAllAccordions() { return accordions; }

    public void onHarmonicaClick() {

        RecyclerView view  = binding.harmonicaMakeOrderList;

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

        RecyclerView view  = binding.bayanMakeOrderList;

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

        RecyclerView view  = binding.accordionMakeOrderList;

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

    public void makeOrder() {

        boolean pickup = binding.pickup.isChecked();

        int price = getHarmonicasSummaryPrice() + getBayansSummaryPrice() + getAccordionsSummaryPrice();

        assert harmonicas.getValue() != null;
        assert bayans.getValue() != null;
        assert accordions.getValue() != null;

        byte[] harmonicaIds = new byte[harmonicas.getValue().size()];
        byte[] bayanIds = new byte[bayans.getValue().size()];
        byte[] accordionIds = new byte[accordions.getValue().size()];

        for (int i = 0; i < harmonicas.getValue().size(); i++) {
            harmonicaIds[i] = (byte) harmonicas.getValue().get(i).getId();
        }

        for (int i = 0; i < bayans.getValue().size(); i++) {
            bayanIds[i] = (byte) bayans.getValue().get(i).getId();
        }

        for (int i = 0; i < accordions.getValue().size(); i++) {
            accordionIds[i] = (byte) accordions.getValue().get(i).getId();
        }

        String deliveryAddress = binding.inputDeliveryAddress.getText().toString();

        Order order = new Order(harmonicaIds, bayanIds, accordionIds, price, MainActivity.currentUser.getId(), pickup, deliveryAddress);

        OrderRepository repository = new OrderRepository(MainActivity.INSTANCE.getApplication());

        repository.insertOrder(order);

        for (Harmonica harmonica: harmonicas.getValue()) {
            repository.insertHarmonica(harmonica);
        }

        for (Bayan bayan : bayans.getValue()) {
            repository.insertBayan(bayan);
        }

        for (Accordion accordion: accordions.getValue()) {
            repository.insertAccordion(accordion);
        }

        new CartRepository(MainActivity.INSTANCE.getApplication()).deleteEverything();

        MainActivity.INSTANCE.setFragment(CartFragment.class,
                R.id.make_order_layout, null,
                MainActivity.INSTANCE.getResources().getString(R.string.title_cart),
                MakeOrderFragment.class);

    }

    public int getHarmonicasSummaryPrice() {

        int price = 0;

        assert harmonicas.getValue() != null;
        if (!harmonicas.getValue().isEmpty()) {
            for (Harmonica harmonica: harmonicas.getValue()) {
                price += harmonica.getPrice();
            }
        }

        return price;
    }

    public int getBayansSummaryPrice() {

        int price = 0;

        assert bayans.getValue() != null;
        if (!bayans.getValue().isEmpty()) {
            for (Bayan bayan: bayans.getValue()) {
                price += bayan.getPrice();
            }
        }

        return price;
    }

    public int getAccordionsSummaryPrice() {

        int price = 0;

        assert accordions.getValue() != null;
        if (!accordions.getValue().isEmpty()) {
            for (Accordion accordion: accordions.getValue()) {
                price += accordion.getPrice();
            }
        }

        return price;
    }

    public void setBinding(FragmentMakeOrderBinding binding) {
        this.binding = binding;
    }
}
