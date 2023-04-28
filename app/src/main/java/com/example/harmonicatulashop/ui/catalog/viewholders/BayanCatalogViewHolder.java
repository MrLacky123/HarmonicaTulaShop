package com.example.harmonicatulashop.ui.catalog.viewholders;

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

public class BayanCatalogViewHolder extends RecyclerView.ViewHolder {

    private final TextView bayanNameView;

    private final TextView bayanPriceView;

    private final ImageView bayanImageView;

    public BayanCatalogViewHolder(View itemView) {
        super(itemView);

        bayanNameView = itemView.findViewById(R.id.bayan_name);
        bayanPriceView = itemView.findViewById(R.id.bayan_price);
        bayanImageView = itemView.findViewById(R.id.bayan_icon);

    }

    public void bindName(String name) {
        bayanNameView.setText(name);
    }

    public void bindPrice(String price) {
        bayanPriceView.setText(price);
    }

    public void bindImage(int id, byte[] image) {

        Bitmap bitmap;

        if (MainActivity.bayanImages.containsKey(id)) {

            bitmap = MainActivity.bayanImages.get(id);

        } else {

            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            MainActivity.bayanImages.put(id, bitmap);

        }

        bayanImageView.setImageBitmap(bitmap);

    }

    public static BayanCatalogViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.catalog_item_bayan, parent, false);
        return new BayanCatalogViewHolder(view);
    }
}