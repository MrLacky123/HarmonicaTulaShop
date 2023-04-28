package com.example.harmonicatulashop.database.catalog.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.ui.catalog.viewholders.BayanCatalogViewHolder;
import com.example.harmonicatulashop.models.catalog.Bayan;

public class BayanCatalogAdapter extends ListAdapter<Bayan, BayanCatalogViewHolder> {
    public BayanCatalogAdapter(@NonNull DiffUtil.ItemCallback<Bayan> diffCallback) {
        super(diffCallback);
    }

    @Override
    public BayanCatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BayanCatalogViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(BayanCatalogViewHolder holder, int position) {
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
