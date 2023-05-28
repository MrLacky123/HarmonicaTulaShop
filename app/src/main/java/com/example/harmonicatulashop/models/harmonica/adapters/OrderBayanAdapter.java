package com.example.harmonicatulashop.models.harmonica.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.viewholders.BayanViewHolder;

import java.util.ArrayList;

public class OrderBayanAdapter extends RecyclerView.Adapter<BayanViewHolder> {

    private BayanAdapter.OnItemClickListener listener;

    ArrayList<Bayan> bayans;

    public OrderBayanAdapter(ArrayList<Bayan> bayans) {
        this.bayans = bayans;
    }

    @NonNull
    @Override
    public BayanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BayanViewHolder.create(parent, listener, bayans);
    }

    @Override
    public void onBindViewHolder(@NonNull BayanViewHolder holder, int position) {

        Bayan current = bayans.get(position);

        String name = Bayan.NAME + " " + current.getType() + " " + current.getRange();
        holder.bindName(name);

        String price = current.getPrice() + " ₽";
        holder.bindPrice(price);

        holder.bindImage(current.getId(), current.getIcon());

        holder.itemView.setTag(current.getId());
    }

    public void setOnItemClickListener(BayanAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return bayans.size();
    }
}
