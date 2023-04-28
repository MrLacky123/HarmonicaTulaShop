package com.example.harmonicatulashop.ui.favourite.viewholders;

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

public class AccordionFavouriteViewHolder extends RecyclerView.ViewHolder {

    private final TextView accordionNameView;

    private final TextView accordionPriceView;

    private final ImageView accordionImageView;

    public AccordionFavouriteViewHolder(View itemView) {
        super(itemView);

        accordionNameView = itemView.findViewById(R.id.cart_accordion_name);
        accordionPriceView = itemView.findViewById(R.id.cart_accordion_price);
        accordionImageView = itemView.findViewById(R.id.cart_accordion_icon);

    }

    public void bindName(String name) {
        accordionNameView.setText(name);
    }

    public void bindPrice(String price) {
        accordionPriceView.setText(price);
    }

    public void bindImage(int id, byte[] image) {

        Bitmap bitmap;

        if (MainActivity.accordionImages.containsKey(id)) {

            bitmap = MainActivity.accordionImages.get(id);

        } else {

            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            MainActivity.accordionImages.put(id, bitmap);

        }

        accordionImageView.setImageBitmap(bitmap);

    }

    public static AccordionFavouriteViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_accordion, parent, false);
        return new AccordionFavouriteViewHolder(view);
    }
}
