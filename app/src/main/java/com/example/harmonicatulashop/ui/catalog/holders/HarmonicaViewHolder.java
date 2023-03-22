package com.example.harmonicatulashop.ui.catalog.holders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.R;

public class HarmonicaViewHolder extends RecyclerView.ViewHolder {

    private final TextView harmonicaNameView;

    private HarmonicaViewHolder(View itemView) {
        super(itemView);
        harmonicaNameView = itemView.findViewById(R.id.harmonica_name);
    }

    public void bind(String text) {
        harmonicaNameView.setText(text);
    }

    public static HarmonicaViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_harmonica, parent, false);
        return new HarmonicaViewHolder(view);
    }
}
