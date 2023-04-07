package com.example.harmonicatulashop.ui.catalog.holders;

import android.content.res.loader.ResourcesLoader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.google.common.io.Resources;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    public void bindImage(byte[] image) {

        new Thread(() -> {

            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

            harmonicaImageView.setImageBitmap(bitmap);

        }).start();
    }

    public static HarmonicaViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_harmonica, parent, false);
        return new HarmonicaViewHolder(view);
    }
}
