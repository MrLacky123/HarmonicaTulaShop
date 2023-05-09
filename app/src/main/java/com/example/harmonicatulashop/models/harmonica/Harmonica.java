package com.example.harmonicatulashop.models.harmonica;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

@Entity(tableName = "harmonica_list")
public class Harmonica {

    public static final String NAME = "Гармонь";

    public static final String ID = "com.example.harmonicatulashop.ID";
    public static final String ICON = "com.example.harmonicatulashop.ICON";
    public static final String TYPE = "com.example.harmonicatulashop.TYPE";
    public static final String TONE = "com.example.harmonicatulashop.TONE";
    public static final String RANGE = "com.example.harmonicatulashop.RANGE";
    public static final String PRICE = "com.example.harmonicatulashop.PRICE";
    public static final String OPTIONS = "com.example.harmonicatulashop.OPTIONS";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "image")
    private byte[] icon;
    @NonNull
    @ColumnInfo(name = "type")
    private String type;
    @NonNull
    @ColumnInfo(name = "tone")
    private String tone;
    @NonNull
    @ColumnInfo(name = "range")
    private String range;
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "options")
    private String options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
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
    public String getTone() {
        return tone;
    }

    public void setTone(@NonNull String tone) {
        this.tone = tone;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Harmonica(@NonNull byte[] icon, @NonNull String type, @NonNull String tone, @NonNull String range, int price, @NonNull String options) {
        this.icon = icon;
        this.type = type;
        this.tone = tone;
        this.range = range;
        this.price = price;
        this.options = options;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Harmonica){
            Harmonica h = (Harmonica) obj;
            return Arrays.equals(this.icon, h.getIcon()) && this.type.equals(h.getType())
                    && this.tone.equals(h.getTone()) && this.range.equals(h.getRange())
                    && this.price == h.getPrice() && this.options.equals(h.getOptions());
        }
        return false;
    }
}
