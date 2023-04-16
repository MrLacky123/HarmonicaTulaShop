package com.example.harmonicatulashop.models.harmonica;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "harmonica_list")
public class Harmonica extends Harmonicas {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public static final String NAME = "Гармонь";

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

    @NonNull
    @ColumnInfo(name = "price")
    private int price;

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

    public Harmonica() {
    }

    public Harmonica(byte[] iconUri, @NonNull String type, @NonNull String tone, @NonNull String range, int price, @NonNull String options) {
        this.icon = iconUri;
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
