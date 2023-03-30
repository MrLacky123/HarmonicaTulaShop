package com.example.harmonicatulashop.ui.catalog.holders;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.R;
import com.squareup.picasso.Picasso;

public class HarmonicaViewHolder extends RecyclerView.ViewHolder {

    private final TextView harmonicaNameView;

    private final TextView harmonicaPriceView;

    private final ImageView harmonicaImageView;

    private HarmonicaViewHolder(View itemView) {

        super(itemView);

        harmonicaNameView = itemView.findViewById(R.id.harmonica_name);
        harmonicaPriceView = itemView.findViewById(R.id.harmonica_price);
        harmonicaImageView = itemView.findViewById(R.id.harmonica_icon);

    }

    public void bindName(String name) {
        harmonicaNameView.setText(name);
    }

    public void bindPrice(String price) {
        harmonicaPriceView.setText(price);
    }

    public void bindImage(String imageUrl) {
        Picasso.with(harmonicaImageView.getContext()).load(imageUrl).into(harmonicaImageView);
    }

    public static HarmonicaViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_harmonica, parent, false);
        return new HarmonicaViewHolder(view);
    }
}
