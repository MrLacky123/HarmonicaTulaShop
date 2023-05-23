package com.example.harmonicatulashop.models.order.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.models.order.Order;
import com.example.harmonicatulashop.models.order.adapters.OrderAdapter;

import java.util.List;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    private final TextView orderNameView;

    private final TextView orderPriceView;

    private OrderViewHolder(View itemView, OrderAdapter.OnItemClickListener listener, List<Order> orders) {
        super(itemView);

        orderNameView = itemView.findViewById(R.id.order_name);
        orderPriceView = itemView.findViewById(R.id.order_price);

        itemView.setOnClickListener(v -> {

            int position = getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onItemClick(orders.get(position));
            }
        });
    }

    public void bindName(String name) {
        orderNameView.setText(name);
    }

    public void bindPrice(String price) {
        orderPriceView.setText(price);
    }

    public static OrderViewHolder create(ViewGroup parent, OrderAdapter.OnItemClickListener listener, List<Order> orders){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_layout, parent, false);

        return new OrderViewHolder(view, listener, orders);
    }
}
