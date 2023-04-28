package com.example.harmonicatulashop.database.catalog.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.ui.catalog.viewholders.AccordionCatalogViewHolder;
import com.example.harmonicatulashop.models.catalog.Accordion;

public class AccordionCatalogAdapter extends ListAdapter<Accordion, AccordionCatalogViewHolder> {

    public AccordionCatalogAdapter(@NonNull DiffUtil.ItemCallback<Accordion> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AccordionCatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AccordionCatalogViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(AccordionCatalogViewHolder holder, int position) {
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
