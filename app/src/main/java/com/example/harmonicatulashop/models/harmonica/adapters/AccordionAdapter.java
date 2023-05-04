package com.example.harmonicatulashop.models.harmonica.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.harmonica.viewholders.AccordionViewHolder;
import com.example.harmonicatulashop.models.harmonica.Accordion;

public class AccordionAdapter extends ListAdapter<Accordion, AccordionViewHolder> {

    private OnItemClickListener listener;

    public AccordionAdapter(@NonNull DiffUtil.ItemCallback<Accordion> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AccordionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AccordionViewHolder.create(parent, listener, getCurrentList());
    }

    @Override
    public void onBindViewHolder(AccordionViewHolder holder, int position) {
        Accordion current = getItem(position);
        String name = Accordion.NAME + " " + current.getRange();
        holder.bindName(name);
        String price = current.getPrice() + " ₽";
        holder.bindPrice(price);
        holder.bindImage(current.getId(), current.getIcon());
    }

    public interface OnItemClickListener {
        void onItemClick(Accordion accordion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
