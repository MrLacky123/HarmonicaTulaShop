package com.example.harmonicatulashop.ui.catalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Harmonica implements Parcelable {

    public static final String NAME = "Гармонь";

    private String iconUri;

    private String type;

    private String tone;

    private String range;

    private int price;

    private ArrayList<String> options;

    public void setIconUrl(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getIconUrl() {
        return iconUri;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public String getTone() {
        return tone;
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

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public Harmonica() {}

    public Harmonica(String iconUrl, String type, String tone, String range, int price, ArrayList<String> options) {
        this.iconUri = iconUrl;
        this.type = type;
        this.tone = tone;
        this.range = range;
        this.price = price;
        this.options = options;
    }

    protected Harmonica(Parcel in) {
        iconUri = in.readString();
        type = in.readString();
        tone = in.readString();
        range = in.readString();
        price = in.readInt();
        options = in.createStringArrayList();
    }

    public static final Creator<Harmonica> CREATOR = new Creator<Harmonica>() {
        @Override
        public Harmonica createFromParcel(Parcel in) {
            return new Harmonica(in);
        }

        @Override
        public Harmonica[] newArray(int size) {
            return new Harmonica[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(iconUri);
        dest.writeString(type);
        dest.writeString(tone);
        dest.writeString(range);
        dest.writeInt(price);
        dest.writeStringList(options);
    }
}
