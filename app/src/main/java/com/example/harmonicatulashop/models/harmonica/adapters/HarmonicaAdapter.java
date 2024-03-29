package com.example.harmonicatulashop.models.harmonica.adapters;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.harmonica.viewholders.HarmonicaViewHolder;
import com.example.harmonicatulashop.models.harmonica.Harmonica;

public class HarmonicaAdapter extends ListAdapter<Harmonica, HarmonicaViewHolder> {

    private OnItemClickListener listener;

    public HarmonicaAdapter(@NonNull DiffUtil.ItemCallback<Harmonica> diffCallback) {
        super(diffCallback);
    }

    @Override
    public HarmonicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HarmonicaViewHolder.create(parent, listener, getCurrentList());
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(HarmonicaViewHolder holder, int position) {

        Harmonica current = getItem(position);

        String name = Harmonica.NAME + " " + current.getType() + " " + current.getTone() + " " + current.getRange();
        holder.bindName(name);

        String price = current.getPrice() + " ₽";
        holder.bindPrice(price);

        holder.bindImage(current.getId(), current.getIcon());

        holder.itemView.setTag(current.getId());
    }

    public interface OnItemClickListener {
        void onItemClick(Harmonica harmonica);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
