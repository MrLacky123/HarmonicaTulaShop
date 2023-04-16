package com.example.harmonicatulashop.models.harmonica;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "accordion_list")
public class Accordion extends Harmonicas {

    public static final String NAME = "Аккордеон ТУЛА";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "image")
    private byte[] icon;

    @NonNull
    @ColumnInfo(name = "range")
    private String range;

    @NonNull
    @ColumnInfo(name = "price")
    private int price;


    @NonNull
    @ColumnInfo(name = "options")
    private String options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Accordion() {}

    public Accordion(byte[] icon, String range, int price, String options) {
        this.icon = icon;
        this.range = range;
        this.price = price;
        this.options = options;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Harmonica){
            Harmonica h = (Harmonica) obj;
            return Arrays.equals(this.icon, h.getIcon()) && this.range.equals(h.getRange())
                    && this.price == h.getPrice() && this.options.equals(h.getOptions());
        }
        return false;
    }
}
