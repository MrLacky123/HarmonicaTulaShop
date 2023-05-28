package com.example.harmonicatulashop.models.harmonica.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.viewholders.AccordionViewHolder;

import java.util.ArrayList;

public class OrderAccordionAdapter extends RecyclerView.Adapter<AccordionViewHolder> {

    private AccordionAdapter.OnItemClickListener listener;

    ArrayList<Accordion> accordions;

    public OrderAccordionAdapter(ArrayList<Accordion> accordions) {
        this.accordions = accordions;
    }

    @NonNull
    @Override
    public AccordionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AccordionViewHolder.create(parent, listener, accordions);
    }

    @Override
    public void onBindViewHolder(@NonNull AccordionViewHolder holder, int position) {

        Accordion current = accordions.get(position);

        String name = Accordion.NAME + " " + current.getRange();
        holder.bindName(name);

        String price = current.getPrice() + " ₽";
        holder.bindPrice(price);

        holder.bindImage(current.getId(), current.getIcon());

        holder.itemView.setTag(current.getId());
    }

    public void setListener(AccordionAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return accordions.size();
    }
}
