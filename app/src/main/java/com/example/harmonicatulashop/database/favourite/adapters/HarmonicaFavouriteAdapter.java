package com.example.harmonicatulashop.database.favourite.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.favourite.Harmonica;
import com.example.harmonicatulashop.ui.cart.viewholders.HarmonicaCartViewHolder;

public class HarmonicaFavouriteAdapter extends ListAdapter<Harmonica, HarmonicaCartViewHolder> {

    public HarmonicaFavouriteAdapter(@NonNull DiffUtil.ItemCallback<Harmonica> diffCallback) {
        super(diffCallback);
    }

    @Override
    public HarmonicaCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HarmonicaCartViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(HarmonicaCartViewHolder holder, int position) {
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
