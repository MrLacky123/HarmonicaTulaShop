package com.example.harmonicatulashop.models.order.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.order.Order;
import com.example.harmonicatulashop.models.order.viewholders.OrderViewHolder;

public class OrderAdapter extends ListAdapter<Order, OrderViewHolder> {

    private OnItemClickListener listener;

    public OrderAdapter(@NonNull DiffUtil.ItemCallback<Order> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return OrderViewHolder.create(parent, listener, getCurrentList());
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        Order current = getItem(position);

        String name = Order.NAME + " №" + current.getNumber();
        holder.bindName(name);

        String price = String.valueOf(current.getPrice());
        holder.bindPrice(price);

        holder.itemView.setTag(current.getId());
    }

    public interface OnItemClickListener {
        void onItemClick(Order order);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class OrderDiff extends DiffUtil.ItemCallback<Order> {

        @Override
        public boolean areItemsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return oldItem.getNumber() == oldItem.getNumber();
        }
    }
}
