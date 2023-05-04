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
import com.example.harmonicatulashop.models.harmonica.Bayan;
import com.example.harmonicatulashop.models.harmonica.adapters.BayanAdapter;

import java.util.List;

public class BayanViewHolder extends RecyclerView.ViewHolder {

    private final TextView bayanNameView;

    private final TextView bayanPriceView;

    private final ImageView bayanImageView;

    public BayanViewHolder(View itemView, BayanAdapter.OnItemClickListener listener, List<Bayan> bayanList) {
        super(itemView);

        bayanNameView = itemView.findViewById(R.id.bayan_name);
        bayanPriceView = itemView.findViewById(R.id.bayan_price);
        bayanImageView = itemView.findViewById(R.id.bayan_icon);

        itemView.setOnClickListener(v -> {

            int position = getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onItemClick(bayanList.get(position));
            }
        });
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

    public static BayanViewHolder create(ViewGroup parent, BayanAdapter.OnItemClickListener listener, List<Bayan> bayanList) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bayan, parent, false);
        return new BayanViewHolder(view, listener, bayanList);
    }
}
