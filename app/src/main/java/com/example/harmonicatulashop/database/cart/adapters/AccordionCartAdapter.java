package com.example.harmonicatulashop.database.cart.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.cart.Accordion;
import com.example.harmonicatulashop.ui.cart.viewholders.AccordionCartViewHolder;

public class AccordionCartAdapter extends ListAdapter<Accordion, AccordionCartViewHolder> {

    public AccordionCartAdapter(@NonNull DiffUtil.ItemCallback<Accordion> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AccordionCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AccordionCartViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(AccordionCartViewHolder holder, int position) {
        Accordion current = getItem(position);
        String name = Accordion.NAME + " " + current.getRange();
        holder.bindName(name);
        String price = current.getPrice() + " ₽";
        holder.bindPrice(price);
        holder.bindImage(current.getId(), current.getIcon());
    }

    public static class AccordionDiff extends DiffUtil.ItemCallback<Accordion> {

        @Override
        public boolean areItemsTheSame(@NonNull Accordion oldItem, @NonNull Accordion newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Accordion oldItem, @NonNull Accordion newItem) {
            return oldItem.equals(newItem);
        }
    }

}
