package com.example.harmonicatulashop.ui.catalog.db.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.ui.catalog.holders.HarmonicaViewHolder;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

public class HarmonicaAdapter extends ListAdapter<Harmonica, HarmonicaViewHolder> {

    public HarmonicaAdapter(@NonNull DiffUtil.ItemCallback<Harmonica> diffCallback) {
        super(diffCallback);
    }

    @Override
    public HarmonicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HarmonicaViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(HarmonicaViewHolder holder, int position) {
        Harmonica current = getItem(position);
        String name = Harmonica.NAME + " " + current.getType() + " " + current.getTone() + " " + current.getRange();
        holder.bindName(name);
        String price = current.getPrice() + " ₽";
        holder.bindPrice(price);
        holder.bindImage(current.getId(), current.getIcon());
    }

    public static class HarmonicaDiff extends DiffUtil.ItemCallback<Harmonica> {

        @Override
        public boolean areItemsTheSame(@NonNull Harmonica oldItem, @NonNull Harmonica newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Harmonica oldItem, @NonNull Harmonica newItem) {
            return oldItem.equals(newItem);
        }
    }
}
