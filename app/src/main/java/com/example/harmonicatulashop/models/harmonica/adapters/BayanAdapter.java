package com.example.harmonicatulashop.models.harmonica.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.harmonica.viewholders.BayanViewHolder;
import com.example.harmonicatulashop.models.harmonica.Bayan;

public class BayanAdapter extends ListAdapter<Bayan, BayanViewHolder> {

    private OnItemClickListener listener;

    public BayanAdapter(@NonNull DiffUtil.ItemCallback<Bayan> diffCallback) {
        super(diffCallback);
    }

    @Override
    public BayanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BayanViewHolder.create(parent, listener, getCurrentList());
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

    public interface OnItemClickListener {
        void onItemClick(Bayan bayan);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
