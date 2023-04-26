package com.example.harmonicatulashop.models.harmonica.viewholders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;

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

    public void bindImage(int id, byte[] image) {

        Bitmap bitmap;

        if (MainActivity.harmonicaImages.containsKey(id)) {

            bitmap = MainActivity.harmonicaImages.get(id);

        } else {

            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            MainActivity.harmonicaImages.put(id, bitmap);

        }

        harmonicaImageView.setImageBitmap(bitmap);
    }

    public static HarmonicaViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_harmonica, parent, false);
        return new HarmonicaViewHolder(view);
    }
}
