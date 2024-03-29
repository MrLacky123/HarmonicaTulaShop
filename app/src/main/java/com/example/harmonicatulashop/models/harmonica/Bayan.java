package com.example.harmonicatulashop.models.harmonica;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "bayan_list")
public class Bayan {

    public static final String ID = "com.example.harmonicatulashop.models.harmonica.Bayan.ID";
    public static final String ICON = "com.example.harmonicatulashop.models.harmonica.Bayan.ICON";
    public static final String TYPE = "com.example.harmonicatulashop.models.harmonica.Bayan.TYPE";
    public static final String RANGE = "com.example.harmonicatulashop.models.harmonica.Bayan.RANGE";
    public static final String PRICE = "com.example.harmonicatulashop.models.harmonica.Bayan.PRICE";
    public static final String OPTIONS = "com.example.harmonicatulashop.models.harmonica.Bayan.OPTIONS";

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(@NonNull byte[] icon) {
        this.icon = icon;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
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

    @NonNull
    public String getOptions() {
        return options;
    }

    public void setOptions(@NonNull String options) {
        this.options = options;
    }

    public Bayan() {}

    public Bayan(@NonNull byte[] icon, @NonNull String type, @NonNull String range, int price, @NonNull String options) {
        this.icon = icon;
        this.type = type;
        this.range = range;
        this.price = price;
        this.options = options;
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
