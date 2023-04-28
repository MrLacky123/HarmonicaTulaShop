package com.example.harmonicatulashop.database.cart.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.cart.Harmonica;
import com.example.harmonicatulashop.ui.cart.viewholders.HarmonicaCartViewHolder;
import com.example.harmonicatulashop.ui.favourite.viewholders.HarmonicaFavouriteViewHolder;

public class HarmonicaCartAdapter extends ListAdapter<Harmonica, HarmonicaFavouriteViewHolder> {

    public HarmonicaCartAdapter(@NonNull DiffUtil.ItemCallback<Harmonica> diffCallback) {
        super(diffCallback);
    }

    @Override
    public HarmonicaFavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HarmonicaFavouriteViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(HarmonicaFavouriteViewHolder holder, int position) {
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
