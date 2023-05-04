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
import com.example.harmonicatulashop.models.harmonica.Accordion;
import com.example.harmonicatulashop.models.harmonica.adapters.AccordionAdapter;

import java.util.List;

public class AccordionViewHolder extends RecyclerView.ViewHolder {

    private final TextView accordionNameView;

    private final TextView accordionPriceView;

    private final ImageView accordionImageView;

    public AccordionViewHolder(View itemView, AccordionAdapter.OnItemClickListener listener, List<Accordion> accordionList) {
        super(itemView);

        accordionNameView = itemView.findViewById(R.id.accordion_name);
        accordionPriceView = itemView.findViewById(R.id.accordion_price);
        accordionImageView = itemView.findViewById(R.id.accordion_icon);

        itemView.setOnClickListener(v -> {

            int position = getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onItemClick(accordionList.get(position));
            }
        });
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

    public static AccordionViewHolder create(ViewGroup parent, AccordionAdapter.OnItemClickListener listener, List<Accordion> accordionList) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_accordion, parent, false);
        return new AccordionViewHolder(view, listener, accordionList);
    }
}
