package com.example.harmonicatulashop.models.harmonica.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.models.harmonica.Harmonica;
import com.example.harmonicatulashop.models.harmonica.viewholders.HarmonicaViewHolder;

import java.util.ArrayList;

public class OrderHarmonicaAdapter extends RecyclerView.Adapter<HarmonicaViewHolder> {

    private HarmonicaAdapter.OnItemClickListener listener;

    ArrayList<Harmonica> harmonicas;

    public OrderHarmonicaAdapter(ArrayList<Harmonica> harmonicas) {
        this.harmonicas = harmonicas;
    }

    @NonNull
    @Override
    public HarmonicaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return HarmonicaViewHolder.create(parent, listener, harmonicas);
    }

    @Override
    public void onBindViewHolder(@NonNull HarmonicaViewHolder holder, int position) {

        Harmonica current = harmonicas.get(position);

        String name = Harmonica.NAME + " " + current.getType() + " " + current.getTone() + " " + current.getRange();
        holder.bindName(name);

        String price = current.getPrice() + " ₽";
        holder.bindPrice(price);

        holder.bindImage(current.getId(), current.getIcon());

        holder.itemView.setTag(current.getId());
    }

    public void setOnItemClickListener(HarmonicaAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return harmonicas.size();
    }
}
