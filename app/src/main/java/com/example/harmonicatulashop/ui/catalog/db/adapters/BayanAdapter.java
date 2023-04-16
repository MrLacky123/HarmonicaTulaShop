package com.example.harmonicatulashop.ui.catalog.db.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.ui.catalog.holders.BayanViewHolder;
import com.example.harmonicatulashop.models.harmonica.Bayan;

public class BayanAdapter extends ListAdapter<Bayan, BayanViewHolder> {
    public BayanAdapter(@NonNull DiffUtil.ItemCallback<Bayan> diffCallback) {
        super(diffCallback);
    }

    @Override
    public BayanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BayanViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(BayanViewHolder holder, int position) {
        Bayan current = getItem(position);
        String name = Bayan.NAME + " " + current.getType() + " " + current.getRange();
        holder.bindName(name);
        String price = current.getPrice() + " ₽";
        holder.bindPrice(price);
        holder.bindImage(current.getId(), current.getIcon());
    }

    public static class BayanDiff extends DiffUtil.ItemCallback<Bayan> {

        @Override
        public boolean areItemsTheSame(@NonNull Bayan oldItem, @NonNull Bayan newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Bayan oldItem, @NonNull Bayan newItem) {
            return oldItem.equals(newItem);
        }
    }
}
