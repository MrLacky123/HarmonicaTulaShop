package com.example.harmonicatulashop.database.favourite.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.favourite.Bayan;
import com.example.harmonicatulashop.ui.favourite.viewholders.BayanFavouriteViewHolder;


public class BayanFavouriteAdapter extends ListAdapter<Bayan, BayanFavouriteViewHolder> {
    public BayanFavouriteAdapter(@NonNull DiffUtil.ItemCallback<Bayan> diffCallback) {
        super(diffCallback);
    }

    @Override
    public BayanFavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BayanFavouriteViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(BayanFavouriteViewHolder holder, int position) {
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
