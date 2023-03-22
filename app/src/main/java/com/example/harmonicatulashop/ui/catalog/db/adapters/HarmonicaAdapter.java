package com.example.harmonicatulashop.ui.catalog.db.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.ui.catalog.holders.HarmonicaViewHolder;
import com.example.harmonicatulashop.ui.catalog.models.Harmonica;

public class HarmonicaAdapter extends ListAdapter<Harmonica, HarmonicaViewHolder> {
    public HarmonicaAdapter(@NonNull DiffUtil.ItemCallback<Harmonica> diffCallback) {
        super(diffCallback);
    }

    @Override
    public HarmonicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HarmonicaViewHolder.create(parent);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(HarmonicaViewHolder holder, int position) {
        Harmonica current = getItem(position);
        holder.bind(current.getWord());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Harmonica> {

        @Override
        public boolean areItemsTheSame(@NonNull Harmonica oldItem, @NonNull Harmonica newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Harmonica oldItem, @NonNull Harmonica newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    }
}
