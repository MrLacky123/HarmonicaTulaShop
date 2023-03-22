package com.example.harmonicatulashop.ui.catalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class Accordion implements Parcelable {

    public static final String NAME = "Аккордеон";

    private String iconUri;

    private String range;

    private int price;

    private ArrayList<String> options;

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
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

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public Accordion() {}


    protected Accordion(Parcel in) {
        iconUri = in.readString();
        range = in.readString();
        options = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iconUri);
        dest.writeString(range);
        dest.writeStringList(options);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Accordion> CREATOR = new Creator<Accordion>() {
        @Override
        public Accordion createFromParcel(Parcel in) {
            return new Accordion(in);
        }

        @Override
        public Accordion[] newArray(int size) {
            return new Accordion[size];
        }
    };
}
