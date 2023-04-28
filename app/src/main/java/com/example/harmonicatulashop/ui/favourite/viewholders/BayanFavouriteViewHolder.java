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

public class BayanFavouriteViewHolder extends RecyclerView.ViewHolder {

    private final TextView bayanNameView;

    private final TextView bayanPriceView;

    private final ImageView bayanImageView;

    public BayanFavouriteViewHolder(View itemView) {
        super(itemView);

        bayanNameView = itemView.findViewById(R.id.cart_bayan_name);
        bayanPriceView = itemView.findViewById(R.id.cart_bayan_price);
        bayanImageView = itemView.findViewById(R.id.cart_bayan_icon);

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

    public static BayanFavouriteViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_bayan, parent, false);
        return new BayanFavouriteViewHolder(view);
    }
}