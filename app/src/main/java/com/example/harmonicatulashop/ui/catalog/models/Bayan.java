package com.example.harmonicatulashop.ui.catalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

public class Bayan implements Parcelable {

    private int id;

    public static final String NAME = "Баян";

    private String range;

    private int price;

    private String iconUri;

    private ArrayList<String> options = new ArrayList<>();

    public Bayan() {}

    public Bayan(String range, int price, String iconUri, ArrayList<String> options) {
        this.range = range;
        this.price = price;
        this.iconUri = iconUri;
        this.options = options;
    }

    protected Bayan(Parcel in) {
        range = in.readString();
        price = in.readInt();
        iconUri = in.readString();
        options = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(range);
        dest.writeString(iconUri);
        dest.writeStringList(options);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bayan> CREATOR = new Creator<Bayan>() {
        @Override
        public Bayan createFromParcel(Parcel in) {
            return new Bayan(in);
        }

        @Override
        public Bayan[] newArray(int size) {
            return new Bayan[size];
        }
    };

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

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
