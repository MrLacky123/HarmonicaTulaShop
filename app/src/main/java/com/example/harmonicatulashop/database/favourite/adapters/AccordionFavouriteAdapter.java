package com.example.harmonicatulashop.database.favourite.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.favourite.Accordion;
import com.example.harmonicatulashop.ui.favourite.viewholders.AccordionFavouriteViewHolder;

public class AccordionFavouriteAdapter extends ListAdapter<Accordion, AccordionFavouriteViewHolder> {

    public AccordionFavouriteAdapter(@NonNull DiffUtil.ItemCallback<Accordion> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AccordionFavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AccordionFavouriteViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(AccordionFavouriteViewHolder holder, int position) {
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
