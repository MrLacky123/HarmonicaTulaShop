package com.example.harmonicatulashop.ui.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Arrays;

@Entity(tableName = "bayan_list")
public class Bayan extends Harmonicas {


    public static final String NAME = "Баян";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "image")
    private byte[] icon;

    @NonNull
    @ColumnInfo(name = "type")
    private String type;

    @NonNull
    @ColumnInfo(name = "range")
    private String range;

    @NonNull
    @ColumnInfo(name = "price")
    private int price;


    @NonNull
    @ColumnInfo(name = "options")
    private String options;

    public Bayan() {}

    public Bayan(@NonNull byte[] icon, @NonNull String type, @NonNull String range, int price, @NonNull String options) {
        this.icon = icon;
        this.type = type;
        this.range = range;
        this.price = price;
        this.options = options;
    }

    @NonNull
    public String getRange() {
        return range;
    }

    public void setRange(@NonNull String range) {
        this.range = range;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(@NonNull byte[] icon) {
        this.icon = icon;
    }

    @NonNull
    public String getOptions() {
        return options;
    }

    public void setOptions(@NonNull String options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Harmonica){
            Harmonica h = (Harmonica) obj;
            return Arrays.equals(this.icon, h.getIcon()) && this.type.equals(h.getType()) && this.range.equals(h.getRange())
                    && this.price == h.getPrice() && this.options.equals(h.getOptions());
        }
        return false;
    }
}
