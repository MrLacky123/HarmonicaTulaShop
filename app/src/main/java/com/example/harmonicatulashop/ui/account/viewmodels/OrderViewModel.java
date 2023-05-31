package com.example.harmonicatulashop.ui.account.viewmodels;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.order.room.OrderRoomDatabase;
import com.example.harmonicatulashop.databinding.FragmentOrderBinding;
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.order.Order;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class OrderViewModel extends AndroidViewModel {

    private ArrayList<Harmonica> harmonicas = new ArrayList<>();
    private ArrayList<Bayan> bayans = new ArrayList<>();
    private ArrayList<Accordion> accordions = new ArrayList<>();

    private FragmentOrderBinding binding;

    public OrderViewModel(Application application) {
        super(application);
    }

    public ArrayList<Harmonica> getHarmonicas() {
        return harmonicas;
    }

    public ArrayList<Bayan> getBayans() {
        return bayans;
    }

    public ArrayList<Accordion> getAccordions() {
        return accordions;
    }

    public void setBinding(FragmentOrderBinding binding) {
        this.binding = binding;
    }

    public void delete() {
        harmonicas = new ArrayList<>();
        bayans = new ArrayList<>();
        accordions = new ArrayList<>();
    }

    public void setOrder(Order order) {

        Harmonica harmonica;
        Bayan bayan;
        Accordion accordion;

        Exchanger<Harmonica> harmonicaExchanger = new Exchanger<>();
        Exchanger<Bayan> bayanExchanger = new Exchanger<>();
        Exchanger<Accordion> accordionExchanger = new Exchanger<>();

        for (int id: order.getHarmonicaIds()) {

            new Thread(new GetHarmonica(harmonicaExchanger, id)).start();

            try {
                harmonica = harmonicaExchanger.exchange(null);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            harmonicas.add(harmonica);
        }

        for (int id: order.getBayanIds()) {

            new Thread(new GetBayan(bayanExchanger, id)).start();

            try {
                bayan = bayanExchanger.exchange(null);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            bayans.add(bayan);
        }

        for (int id: order.getAccordionIds()) {

            new Thread(new GetAccordion(accordionExchanger, id)).start();

            try {
                accordion = accordionExchanger.exchange(null);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            accordions.add(accordion);
        }
    }

    public void onHarmonicaClick() {

        RecyclerView view  = binding.harmonicaOrderList;

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

        RecyclerView view  = binding.bayanOrderList;

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

        RecyclerView view  = binding.accordionOrderList;

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

    private static class GetHarmonica implements Runnable{

        Exchanger<Harmonica> exchanger;
        Harmonica harmonica;
        int id;

        public GetHarmonica(Exchanger<Harmonica> exchanger, int id) {
            this.exchanger = exchanger;
            this.id = id;
        }

        @Override
        public void run() {
            harmonica = OrderRoomDatabase.getDatabase(MainActivity.INSTANCE.getApplication()).harmonicaDao().findById(id);
            try {
                harmonica = exchanger.exchange(harmonica);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class GetBayan implements Runnable{

        Exchanger<Bayan> exchanger;
        Bayan bayan;
        int id;

        public GetBayan(Exchanger<Bayan> exchanger, int id) {
            this.exchanger = exchanger;
            this.id = id;
        }

        @Override
        public void run() {
            bayan = OrderRoomDatabase.getDatabase(MainActivity.INSTANCE.getApplication()).bayanDao().findById(id);
            try {
                bayan = exchanger.exchange(bayan);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class GetAccordion implements Runnable{

        Exchanger<Accordion> exchanger;
        Accordion accordion;
        int id;

        public GetAccordion(Exchanger<Accordion> exchanger, int id) {
            this.exchanger = exchanger;
            this.id = id;
        }

        @Override
        public void run() {
            accordion = OrderRoomDatabase.getDatabase(MainActivity.INSTANCE.getApplication()).accordionDao().findById(id);
            try {
                accordion = exchanger.exchange(accordion);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
